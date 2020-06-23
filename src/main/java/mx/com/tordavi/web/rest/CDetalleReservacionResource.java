package mx.com.tordavi.web.rest;

import mx.com.tordavi.service.CDetalleReservacionService;
import mx.com.tordavi.web.rest.errors.BadRequestAlertException;
import mx.com.tordavi.service.dto.CDetalleReservacionDTO;
import mx.com.tordavi.service.dto.CDetalleReservacionCriteria;
import mx.com.tordavi.service.CDetalleReservacionQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link mx.com.tordavi.domain.CDetalleReservacion}.
 */
@RestController
@RequestMapping("/api")
public class CDetalleReservacionResource {

    private final Logger log = LoggerFactory.getLogger(CDetalleReservacionResource.class);

    private static final String ENTITY_NAME = "cDetalleReservacion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CDetalleReservacionService cDetalleReservacionService;

    private final CDetalleReservacionQueryService cDetalleReservacionQueryService;

    public CDetalleReservacionResource(CDetalleReservacionService cDetalleReservacionService, CDetalleReservacionQueryService cDetalleReservacionQueryService) {
        this.cDetalleReservacionService = cDetalleReservacionService;
        this.cDetalleReservacionQueryService = cDetalleReservacionQueryService;
    }

    /**
     * {@code POST  /c-detalle-reservacions} : Create a new cDetalleReservacion.
     *
     * @param cDetalleReservacionDTO the cDetalleReservacionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cDetalleReservacionDTO, or with status {@code 400 (Bad Request)} if the cDetalleReservacion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/c-detalle-reservacions")
    public ResponseEntity<CDetalleReservacionDTO> createCDetalleReservacion(@Valid @RequestBody CDetalleReservacionDTO cDetalleReservacionDTO) throws URISyntaxException {
        log.debug("REST request to save CDetalleReservacion : {}", cDetalleReservacionDTO);
        if (cDetalleReservacionDTO.getId() != null) {
            throw new BadRequestAlertException("A new cDetalleReservacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CDetalleReservacionDTO result = cDetalleReservacionService.save(cDetalleReservacionDTO);
        return ResponseEntity.created(new URI("/api/c-detalle-reservacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /c-detalle-reservacions} : Updates an existing cDetalleReservacion.
     *
     * @param cDetalleReservacionDTO the cDetalleReservacionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cDetalleReservacionDTO,
     * or with status {@code 400 (Bad Request)} if the cDetalleReservacionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cDetalleReservacionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/c-detalle-reservacions")
    public ResponseEntity<CDetalleReservacionDTO> updateCDetalleReservacion(@Valid @RequestBody CDetalleReservacionDTO cDetalleReservacionDTO) throws URISyntaxException {
        log.debug("REST request to update CDetalleReservacion : {}", cDetalleReservacionDTO);
        if (cDetalleReservacionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CDetalleReservacionDTO result = cDetalleReservacionService.save(cDetalleReservacionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cDetalleReservacionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /c-detalle-reservacions} : get all the cDetalleReservacions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cDetalleReservacions in body.
     */
    @GetMapping("/c-detalle-reservacions")
    public ResponseEntity<List<CDetalleReservacionDTO>> getAllCDetalleReservacions(CDetalleReservacionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CDetalleReservacions by criteria: {}", criteria);
        Page<CDetalleReservacionDTO> page = cDetalleReservacionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /c-detalle-reservacions/count} : count all the cDetalleReservacions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/c-detalle-reservacions/count")
    public ResponseEntity<Long> countCDetalleReservacions(CDetalleReservacionCriteria criteria) {
        log.debug("REST request to count CDetalleReservacions by criteria: {}", criteria);
        return ResponseEntity.ok().body(cDetalleReservacionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /c-detalle-reservacions/:id} : get the "id" cDetalleReservacion.
     *
     * @param id the id of the cDetalleReservacionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cDetalleReservacionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/c-detalle-reservacions/{id}")
    public ResponseEntity<CDetalleReservacionDTO> getCDetalleReservacion(@PathVariable Long id) {
        log.debug("REST request to get CDetalleReservacion : {}", id);
        Optional<CDetalleReservacionDTO> cDetalleReservacionDTO = cDetalleReservacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cDetalleReservacionDTO);
    }

    /**
     * {@code DELETE  /c-detalle-reservacions/:id} : delete the "id" cDetalleReservacion.
     *
     * @param id the id of the cDetalleReservacionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/c-detalle-reservacions/{id}")
    public ResponseEntity<Void> deleteCDetalleReservacion(@PathVariable Long id) {
        log.debug("REST request to delete CDetalleReservacion : {}", id);
        cDetalleReservacionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
