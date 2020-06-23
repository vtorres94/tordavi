package mx.com.tordavi.web.rest;

import mx.com.tordavi.service.CPendienteService;
import mx.com.tordavi.web.rest.errors.BadRequestAlertException;
import mx.com.tordavi.service.dto.CPendienteDTO;
import mx.com.tordavi.service.dto.CPendienteCriteria;
import mx.com.tordavi.service.CPendienteQueryService;

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
 * REST controller for managing {@link mx.com.tordavi.domain.CPendiente}.
 */
@RestController
@RequestMapping("/api")
public class CPendienteResource {

    private final Logger log = LoggerFactory.getLogger(CPendienteResource.class);

    private static final String ENTITY_NAME = "cPendiente";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CPendienteService cPendienteService;

    private final CPendienteQueryService cPendienteQueryService;

    public CPendienteResource(CPendienteService cPendienteService, CPendienteQueryService cPendienteQueryService) {
        this.cPendienteService = cPendienteService;
        this.cPendienteQueryService = cPendienteQueryService;
    }

    /**
     * {@code POST  /c-pendientes} : Create a new cPendiente.
     *
     * @param cPendienteDTO the cPendienteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cPendienteDTO, or with status {@code 400 (Bad Request)} if the cPendiente has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/c-pendientes")
    public ResponseEntity<CPendienteDTO> createCPendiente(@Valid @RequestBody CPendienteDTO cPendienteDTO) throws URISyntaxException {
        log.debug("REST request to save CPendiente : {}", cPendienteDTO);
        if (cPendienteDTO.getId() != null) {
            throw new BadRequestAlertException("A new cPendiente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CPendienteDTO result = cPendienteService.save(cPendienteDTO);
        return ResponseEntity.created(new URI("/api/c-pendientes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /c-pendientes} : Updates an existing cPendiente.
     *
     * @param cPendienteDTO the cPendienteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cPendienteDTO,
     * or with status {@code 400 (Bad Request)} if the cPendienteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cPendienteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/c-pendientes")
    public ResponseEntity<CPendienteDTO> updateCPendiente(@Valid @RequestBody CPendienteDTO cPendienteDTO) throws URISyntaxException {
        log.debug("REST request to update CPendiente : {}", cPendienteDTO);
        if (cPendienteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CPendienteDTO result = cPendienteService.save(cPendienteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cPendienteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /c-pendientes} : get all the cPendientes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cPendientes in body.
     */
    @GetMapping("/c-pendientes")
    public ResponseEntity<List<CPendienteDTO>> getAllCPendientes(CPendienteCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CPendientes by criteria: {}", criteria);
        Page<CPendienteDTO> page = cPendienteQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /c-pendientes/count} : count all the cPendientes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/c-pendientes/count")
    public ResponseEntity<Long> countCPendientes(CPendienteCriteria criteria) {
        log.debug("REST request to count CPendientes by criteria: {}", criteria);
        return ResponseEntity.ok().body(cPendienteQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /c-pendientes/:id} : get the "id" cPendiente.
     *
     * @param id the id of the cPendienteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cPendienteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/c-pendientes/{id}")
    public ResponseEntity<CPendienteDTO> getCPendiente(@PathVariable Long id) {
        log.debug("REST request to get CPendiente : {}", id);
        Optional<CPendienteDTO> cPendienteDTO = cPendienteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cPendienteDTO);
    }

    /**
     * {@code DELETE  /c-pendientes/:id} : delete the "id" cPendiente.
     *
     * @param id the id of the cPendienteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/c-pendientes/{id}")
    public ResponseEntity<Void> deleteCPendiente(@PathVariable Long id) {
        log.debug("REST request to delete CPendiente : {}", id);
        cPendienteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
