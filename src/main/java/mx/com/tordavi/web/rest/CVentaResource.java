package mx.com.tordavi.web.rest;

import mx.com.tordavi.service.CVentaService;
import mx.com.tordavi.web.rest.errors.BadRequestAlertException;
import mx.com.tordavi.service.dto.CVentaDTO;
import mx.com.tordavi.service.dto.CVentaCriteria;
import mx.com.tordavi.service.CVentaQueryService;

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
 * REST controller for managing {@link mx.com.tordavi.domain.CVenta}.
 */
@RestController
@RequestMapping("/api")
public class CVentaResource {

    private final Logger log = LoggerFactory.getLogger(CVentaResource.class);

    private static final String ENTITY_NAME = "cVenta";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CVentaService cVentaService;

    private final CVentaQueryService cVentaQueryService;

    public CVentaResource(CVentaService cVentaService, CVentaQueryService cVentaQueryService) {
        this.cVentaService = cVentaService;
        this.cVentaQueryService = cVentaQueryService;
    }

    /**
     * {@code POST  /c-ventas} : Create a new cVenta.
     *
     * @param cVentaDTO the cVentaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cVentaDTO, or with status {@code 400 (Bad Request)} if the cVenta has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/c-ventas")
    public ResponseEntity<CVentaDTO> createCVenta(@Valid @RequestBody CVentaDTO cVentaDTO) throws URISyntaxException {
        log.debug("REST request to save CVenta : {}", cVentaDTO);
        if (cVentaDTO.getId() != null) {
            throw new BadRequestAlertException("A new cVenta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CVentaDTO result = cVentaService.save(cVentaDTO);
        return ResponseEntity.created(new URI("/api/c-ventas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /c-ventas} : Updates an existing cVenta.
     *
     * @param cVentaDTO the cVentaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cVentaDTO,
     * or with status {@code 400 (Bad Request)} if the cVentaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cVentaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/c-ventas")
    public ResponseEntity<CVentaDTO> updateCVenta(@Valid @RequestBody CVentaDTO cVentaDTO) throws URISyntaxException {
        log.debug("REST request to update CVenta : {}", cVentaDTO);
        if (cVentaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CVentaDTO result = cVentaService.save(cVentaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cVentaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /c-ventas} : get all the cVentas.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cVentas in body.
     */
    @GetMapping("/c-ventas")
    public ResponseEntity<List<CVentaDTO>> getAllCVentas(CVentaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CVentas by criteria: {}", criteria);
        Page<CVentaDTO> page = cVentaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /c-ventas/count} : count all the cVentas.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/c-ventas/count")
    public ResponseEntity<Long> countCVentas(CVentaCriteria criteria) {
        log.debug("REST request to count CVentas by criteria: {}", criteria);
        return ResponseEntity.ok().body(cVentaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /c-ventas/:id} : get the "id" cVenta.
     *
     * @param id the id of the cVentaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cVentaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/c-ventas/{id}")
    public ResponseEntity<CVentaDTO> getCVenta(@PathVariable Long id) {
        log.debug("REST request to get CVenta : {}", id);
        Optional<CVentaDTO> cVentaDTO = cVentaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cVentaDTO);
    }

    /**
     * {@code DELETE  /c-ventas/:id} : delete the "id" cVenta.
     *
     * @param id the id of the cVentaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/c-ventas/{id}")
    public ResponseEntity<Void> deleteCVenta(@PathVariable Long id) {
        log.debug("REST request to delete CVenta : {}", id);
        cVentaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
