package mx.com.tordavi.web.rest;

import mx.com.tordavi.service.CDocumentoViajeService;
import mx.com.tordavi.web.rest.errors.BadRequestAlertException;
import mx.com.tordavi.service.dto.CDocumentoViajeDTO;
import mx.com.tordavi.service.dto.CDocumentoViajeCriteria;
import mx.com.tordavi.service.CDocumentoViajeQueryService;

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
 * REST controller for managing {@link mx.com.tordavi.domain.CDocumentoViaje}.
 */
@RestController
@RequestMapping("/api")
public class CDocumentoViajeResource {

    private final Logger log = LoggerFactory.getLogger(CDocumentoViajeResource.class);

    private static final String ENTITY_NAME = "cDocumentoViaje";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CDocumentoViajeService cDocumentoViajeService;

    private final CDocumentoViajeQueryService cDocumentoViajeQueryService;

    public CDocumentoViajeResource(CDocumentoViajeService cDocumentoViajeService, CDocumentoViajeQueryService cDocumentoViajeQueryService) {
        this.cDocumentoViajeService = cDocumentoViajeService;
        this.cDocumentoViajeQueryService = cDocumentoViajeQueryService;
    }

    /**
     * {@code POST  /c-documento-viajes} : Create a new cDocumentoViaje.
     *
     * @param cDocumentoViajeDTO the cDocumentoViajeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cDocumentoViajeDTO, or with status {@code 400 (Bad Request)} if the cDocumentoViaje has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/c-documento-viajes")
    public ResponseEntity<CDocumentoViajeDTO> createCDocumentoViaje(@Valid @RequestBody CDocumentoViajeDTO cDocumentoViajeDTO) throws URISyntaxException {
        log.debug("REST request to save CDocumentoViaje : {}", cDocumentoViajeDTO);
        if (cDocumentoViajeDTO.getId() != null) {
            throw new BadRequestAlertException("A new cDocumentoViaje cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CDocumentoViajeDTO result = cDocumentoViajeService.save(cDocumentoViajeDTO);
        return ResponseEntity.created(new URI("/api/c-documento-viajes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /c-documento-viajes} : Updates an existing cDocumentoViaje.
     *
     * @param cDocumentoViajeDTO the cDocumentoViajeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cDocumentoViajeDTO,
     * or with status {@code 400 (Bad Request)} if the cDocumentoViajeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cDocumentoViajeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/c-documento-viajes")
    public ResponseEntity<CDocumentoViajeDTO> updateCDocumentoViaje(@Valid @RequestBody CDocumentoViajeDTO cDocumentoViajeDTO) throws URISyntaxException {
        log.debug("REST request to update CDocumentoViaje : {}", cDocumentoViajeDTO);
        if (cDocumentoViajeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CDocumentoViajeDTO result = cDocumentoViajeService.save(cDocumentoViajeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cDocumentoViajeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /c-documento-viajes} : get all the cDocumentoViajes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cDocumentoViajes in body.
     */
    @GetMapping("/c-documento-viajes")
    public ResponseEntity<List<CDocumentoViajeDTO>> getAllCDocumentoViajes(CDocumentoViajeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CDocumentoViajes by criteria: {}", criteria);
        Page<CDocumentoViajeDTO> page = cDocumentoViajeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /c-documento-viajes/count} : count all the cDocumentoViajes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/c-documento-viajes/count")
    public ResponseEntity<Long> countCDocumentoViajes(CDocumentoViajeCriteria criteria) {
        log.debug("REST request to count CDocumentoViajes by criteria: {}", criteria);
        return ResponseEntity.ok().body(cDocumentoViajeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /c-documento-viajes/:id} : get the "id" cDocumentoViaje.
     *
     * @param id the id of the cDocumentoViajeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cDocumentoViajeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/c-documento-viajes/{id}")
    public ResponseEntity<CDocumentoViajeDTO> getCDocumentoViaje(@PathVariable Long id) {
        log.debug("REST request to get CDocumentoViaje : {}", id);
        Optional<CDocumentoViajeDTO> cDocumentoViajeDTO = cDocumentoViajeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cDocumentoViajeDTO);
    }

    /**
     * {@code DELETE  /c-documento-viajes/:id} : delete the "id" cDocumentoViaje.
     *
     * @param id the id of the cDocumentoViajeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/c-documento-viajes/{id}")
    public ResponseEntity<Void> deleteCDocumentoViaje(@PathVariable Long id) {
        log.debug("REST request to delete CDocumentoViaje : {}", id);
        cDocumentoViajeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
