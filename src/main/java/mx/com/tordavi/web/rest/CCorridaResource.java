package mx.com.tordavi.web.rest;

import mx.com.tordavi.service.CCorridaService;
import mx.com.tordavi.web.rest.errors.BadRequestAlertException;
import mx.com.tordavi.service.dto.CCorridaDTO;
import mx.com.tordavi.service.dto.CCorridaCriteria;
import mx.com.tordavi.service.CCorridaQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link mx.com.tordavi.domain.CCorrida}.
 */
@RestController
@RequestMapping("/api")
public class CCorridaResource {

    private final Logger log = LoggerFactory.getLogger(CCorridaResource.class);

    private static final String ENTITY_NAME = "cCorrida";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CCorridaService cCorridaService;

    private final CCorridaQueryService cCorridaQueryService;

    public CCorridaResource(CCorridaService cCorridaService, CCorridaQueryService cCorridaQueryService) {
        this.cCorridaService = cCorridaService;
        this.cCorridaQueryService = cCorridaQueryService;
    }

    /**
     * {@code POST  /c-corridas} : Create a new cCorrida.
     *
     * @param cCorridaDTO the cCorridaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cCorridaDTO, or with status {@code 400 (Bad Request)} if the cCorrida has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/c-corridas")
    public ResponseEntity<CCorridaDTO> createCCorrida(@Valid @RequestBody CCorridaDTO cCorridaDTO) throws URISyntaxException {
        log.debug("REST request to save CCorrida : {}", cCorridaDTO);
        if (cCorridaDTO.getId() != null) {
            throw new BadRequestAlertException("A new cCorrida cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CCorridaDTO result = cCorridaService.save(cCorridaDTO);
        return ResponseEntity.created(new URI("/api/c-corridas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /c-corridas} : Updates an existing cCorrida.
     *
     * @param cCorridaDTO the cCorridaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cCorridaDTO,
     * or with status {@code 400 (Bad Request)} if the cCorridaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cCorridaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/c-corridas")
    public ResponseEntity<CCorridaDTO> updateCCorrida(@Valid @RequestBody CCorridaDTO cCorridaDTO) throws URISyntaxException {
        log.debug("REST request to update CCorrida : {}", cCorridaDTO);
        if (cCorridaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CCorridaDTO result = cCorridaService.save(cCorridaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cCorridaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /c-corridas} : get all the cCorridas.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cCorridas in body.
     */
    @GetMapping("/c-corridas")
    public ResponseEntity<List<CCorridaDTO>> getAllCCorridas(CCorridaCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CCorridas by criteria: {}", criteria);
        Page<CCorridaDTO> page = cCorridaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /c-corridas/count} : count all the cCorridas.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/c-corridas/count")
    public ResponseEntity<Long> countCCorridas(CCorridaCriteria criteria) {
        log.debug("REST request to count CCorridas by criteria: {}", criteria);
        return ResponseEntity.ok().body(cCorridaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /c-corridas/:id} : get the "id" cCorrida.
     *
     * @param id the id of the cCorridaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cCorridaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/c-corridas/{id}")
    public ResponseEntity<CCorridaDTO> getCCorrida(@PathVariable Long id) {
        log.debug("REST request to get CCorrida : {}", id);
        Optional<CCorridaDTO> cCorridaDTO = cCorridaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cCorridaDTO);
    }

    /**
     * {@code DELETE  /c-corridas/:id} : delete the "id" cCorrida.
     *
     * @param id the id of the cCorridaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/c-corridas/{id}")
    public ResponseEntity<Void> deleteCCorrida(@PathVariable Long id) {
        log.debug("REST request to delete CCorrida : {}", id);
        cCorridaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
