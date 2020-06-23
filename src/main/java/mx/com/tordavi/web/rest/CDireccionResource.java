package mx.com.tordavi.web.rest;

import mx.com.tordavi.service.CDireccionService;
import mx.com.tordavi.web.rest.errors.BadRequestAlertException;
import mx.com.tordavi.service.dto.CDireccionDTO;
import mx.com.tordavi.service.dto.CDireccionCriteria;
import mx.com.tordavi.service.CDireccionQueryService;

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
 * REST controller for managing {@link mx.com.tordavi.domain.CDireccion}.
 */
@RestController
@RequestMapping("/api")
public class CDireccionResource {

    private final Logger log = LoggerFactory.getLogger(CDireccionResource.class);

    private static final String ENTITY_NAME = "cDireccion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CDireccionService cDireccionService;

    private final CDireccionQueryService cDireccionQueryService;

    public CDireccionResource(CDireccionService cDireccionService, CDireccionQueryService cDireccionQueryService) {
        this.cDireccionService = cDireccionService;
        this.cDireccionQueryService = cDireccionQueryService;
    }

    /**
     * {@code POST  /c-direccions} : Create a new cDireccion.
     *
     * @param cDireccionDTO the cDireccionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cDireccionDTO, or with status {@code 400 (Bad Request)} if the cDireccion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/c-direccions")
    public ResponseEntity<CDireccionDTO> createCDireccion(@Valid @RequestBody CDireccionDTO cDireccionDTO) throws URISyntaxException {
        log.debug("REST request to save CDireccion : {}", cDireccionDTO);
        if (cDireccionDTO.getId() != null) {
            throw new BadRequestAlertException("A new cDireccion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CDireccionDTO result = cDireccionService.save(cDireccionDTO);
        return ResponseEntity.created(new URI("/api/c-direccions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /c-direccions} : Updates an existing cDireccion.
     *
     * @param cDireccionDTO the cDireccionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cDireccionDTO,
     * or with status {@code 400 (Bad Request)} if the cDireccionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cDireccionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/c-direccions")
    public ResponseEntity<CDireccionDTO> updateCDireccion(@Valid @RequestBody CDireccionDTO cDireccionDTO) throws URISyntaxException {
        log.debug("REST request to update CDireccion : {}", cDireccionDTO);
        if (cDireccionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CDireccionDTO result = cDireccionService.save(cDireccionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cDireccionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /c-direccions} : get all the cDireccions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cDireccions in body.
     */
    @GetMapping("/c-direccions")
    public ResponseEntity<List<CDireccionDTO>> getAllCDireccions(CDireccionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CDireccions by criteria: {}", criteria);
        Page<CDireccionDTO> page = cDireccionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /c-direccions/count} : count all the cDireccions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/c-direccions/count")
    public ResponseEntity<Long> countCDireccions(CDireccionCriteria criteria) {
        log.debug("REST request to count CDireccions by criteria: {}", criteria);
        return ResponseEntity.ok().body(cDireccionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /c-direccions/:id} : get the "id" cDireccion.
     *
     * @param id the id of the cDireccionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cDireccionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/c-direccions/{id}")
    public ResponseEntity<CDireccionDTO> getCDireccion(@PathVariable Long id) {
        log.debug("REST request to get CDireccion : {}", id);
        Optional<CDireccionDTO> cDireccionDTO = cDireccionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cDireccionDTO);
    }

    /**
     * {@code DELETE  /c-direccions/:id} : delete the "id" cDireccion.
     *
     * @param id the id of the cDireccionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/c-direccions/{id}")
    public ResponseEntity<Void> deleteCDireccion(@PathVariable Long id) {
        log.debug("REST request to delete CDireccion : {}", id);
        cDireccionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
