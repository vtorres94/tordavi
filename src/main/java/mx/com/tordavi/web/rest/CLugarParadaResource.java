package mx.com.tordavi.web.rest;

import mx.com.tordavi.service.CLugarParadaService;
import mx.com.tordavi.web.rest.errors.BadRequestAlertException;
import mx.com.tordavi.service.dto.CLugarParadaDTO;
import mx.com.tordavi.service.dto.CLugarParadaCriteria;
import mx.com.tordavi.service.CLugarParadaQueryService;

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
 * REST controller for managing {@link mx.com.tordavi.domain.CLugarParada}.
 */
@RestController
@RequestMapping("/api")
public class CLugarParadaResource {

    private final Logger log = LoggerFactory.getLogger(CLugarParadaResource.class);

    private static final String ENTITY_NAME = "cLugarParada";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CLugarParadaService cLugarParadaService;

    private final CLugarParadaQueryService cLugarParadaQueryService;

    public CLugarParadaResource(CLugarParadaService cLugarParadaService, CLugarParadaQueryService cLugarParadaQueryService) {
        this.cLugarParadaService = cLugarParadaService;
        this.cLugarParadaQueryService = cLugarParadaQueryService;
    }

    /**
     * {@code POST  /c-lugar-paradas} : Create a new cLugarParada.
     *
     * @param cLugarParadaDTO the cLugarParadaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cLugarParadaDTO, or with status {@code 400 (Bad Request)} if the cLugarParada has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/c-lugar-paradas")
    public ResponseEntity<CLugarParadaDTO> createCLugarParada(@Valid @RequestBody CLugarParadaDTO cLugarParadaDTO) throws URISyntaxException {
        log.debug("REST request to save CLugarParada : {}", cLugarParadaDTO);
        if (cLugarParadaDTO.getId() != null) {
            throw new BadRequestAlertException("A new cLugarParada cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CLugarParadaDTO result = cLugarParadaService.save(cLugarParadaDTO);
        return ResponseEntity.created(new URI("/api/c-lugar-paradas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /c-lugar-paradas} : Updates an existing cLugarParada.
     *
     * @param cLugarParadaDTO the cLugarParadaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cLugarParadaDTO,
     * or with status {@code 400 (Bad Request)} if the cLugarParadaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cLugarParadaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/c-lugar-paradas")
    public ResponseEntity<CLugarParadaDTO> updateCLugarParada(@Valid @RequestBody CLugarParadaDTO cLugarParadaDTO) throws URISyntaxException {
        log.debug("REST request to update CLugarParada : {}", cLugarParadaDTO);
        if (cLugarParadaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CLugarParadaDTO result = cLugarParadaService.save(cLugarParadaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cLugarParadaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /c-lugar-paradas} : get all the cLugarParadas.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cLugarParadas in body.
     */
    @GetMapping("/c-lugar-paradas")
    public ResponseEntity<List<CLugarParadaDTO>> getAllCLugarParadas(CLugarParadaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CLugarParadas by criteria: {}", criteria);
        Page<CLugarParadaDTO> page = cLugarParadaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /c-lugar-paradas/count} : count all the cLugarParadas.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/c-lugar-paradas/count")
    public ResponseEntity<Long> countCLugarParadas(CLugarParadaCriteria criteria) {
        log.debug("REST request to count CLugarParadas by criteria: {}", criteria);
        return ResponseEntity.ok().body(cLugarParadaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /c-lugar-paradas/:id} : get the "id" cLugarParada.
     *
     * @param id the id of the cLugarParadaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cLugarParadaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/c-lugar-paradas/{id}")
    public ResponseEntity<CLugarParadaDTO> getCLugarParada(@PathVariable Long id) {
        log.debug("REST request to get CLugarParada : {}", id);
        Optional<CLugarParadaDTO> cLugarParadaDTO = cLugarParadaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cLugarParadaDTO);
    }

    /**
     * {@code DELETE  /c-lugar-paradas/:id} : delete the "id" cLugarParada.
     *
     * @param id the id of the cLugarParadaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/c-lugar-paradas/{id}")
    public ResponseEntity<Void> deleteCLugarParada(@PathVariable Long id) {
        log.debug("REST request to delete CLugarParada : {}", id);
        cLugarParadaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
