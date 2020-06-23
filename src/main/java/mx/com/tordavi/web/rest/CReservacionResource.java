package mx.com.tordavi.web.rest;

import mx.com.tordavi.service.CReservacionService;
import mx.com.tordavi.web.rest.errors.BadRequestAlertException;
import mx.com.tordavi.service.dto.CReservacionDTO;
import mx.com.tordavi.service.dto.CReservacionCriteria;
import mx.com.tordavi.service.CReservacionQueryService;

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
 * REST controller for managing {@link mx.com.tordavi.domain.CReservacion}.
 */
@RestController
@RequestMapping("/api")
public class CReservacionResource {

    private final Logger log = LoggerFactory.getLogger(CReservacionResource.class);

    private static final String ENTITY_NAME = "cReservacion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CReservacionService cReservacionService;

    private final CReservacionQueryService cReservacionQueryService;

    public CReservacionResource(CReservacionService cReservacionService, CReservacionQueryService cReservacionQueryService) {
        this.cReservacionService = cReservacionService;
        this.cReservacionQueryService = cReservacionQueryService;
    }

    /**
     * {@code POST  /c-reservacions} : Create a new cReservacion.
     *
     * @param cReservacionDTO the cReservacionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cReservacionDTO, or with status {@code 400 (Bad Request)} if the cReservacion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/c-reservacions")
    public ResponseEntity<CReservacionDTO> createCReservacion(@Valid @RequestBody CReservacionDTO cReservacionDTO) throws URISyntaxException {
        log.debug("REST request to save CReservacion : {}", cReservacionDTO);
        if (cReservacionDTO.getId() != null) {
            throw new BadRequestAlertException("A new cReservacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CReservacionDTO result = cReservacionService.save(cReservacionDTO);
        return ResponseEntity.created(new URI("/api/c-reservacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /c-reservacions} : Updates an existing cReservacion.
     *
     * @param cReservacionDTO the cReservacionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cReservacionDTO,
     * or with status {@code 400 (Bad Request)} if the cReservacionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cReservacionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/c-reservacions")
    public ResponseEntity<CReservacionDTO> updateCReservacion(@Valid @RequestBody CReservacionDTO cReservacionDTO) throws URISyntaxException {
        log.debug("REST request to update CReservacion : {}", cReservacionDTO);
        if (cReservacionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CReservacionDTO result = cReservacionService.save(cReservacionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cReservacionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /c-reservacions} : get all the cReservacions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cReservacions in body.
     */
    @GetMapping("/c-reservacions")
    public ResponseEntity<List<CReservacionDTO>> getAllCReservacions(CReservacionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CReservacions by criteria: {}", criteria);
        Page<CReservacionDTO> page = cReservacionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /c-reservacions/count} : count all the cReservacions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/c-reservacions/count")
    public ResponseEntity<Long> countCReservacions(CReservacionCriteria criteria) {
        log.debug("REST request to count CReservacions by criteria: {}", criteria);
        return ResponseEntity.ok().body(cReservacionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /c-reservacions/:id} : get the "id" cReservacion.
     *
     * @param id the id of the cReservacionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cReservacionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/c-reservacions/{id}")
    public ResponseEntity<CReservacionDTO> getCReservacion(@PathVariable Long id) {
        log.debug("REST request to get CReservacion : {}", id);
        Optional<CReservacionDTO> cReservacionDTO = cReservacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cReservacionDTO);
    }

    /**
     * {@code DELETE  /c-reservacions/:id} : delete the "id" cReservacion.
     *
     * @param id the id of the cReservacionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/c-reservacions/{id}")
    public ResponseEntity<Void> deleteCReservacion(@PathVariable Long id) {
        log.debug("REST request to delete CReservacion : {}", id);
        cReservacionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
