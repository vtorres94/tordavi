package mx.com.tordavi.web.rest;

import mx.com.tordavi.service.CAutobusService;
import mx.com.tordavi.web.rest.errors.BadRequestAlertException;
import mx.com.tordavi.service.dto.CAutobusDTO;
import mx.com.tordavi.service.dto.CAutobusCriteria;
import mx.com.tordavi.service.CAutobusQueryService;

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
 * REST controller for managing {@link mx.com.tordavi.domain.CAutobus}.
 */
@RestController
@RequestMapping("/api")
public class CAutobusResource {

    private final Logger log = LoggerFactory.getLogger(CAutobusResource.class);

    private static final String ENTITY_NAME = "cAutobus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CAutobusService cAutobusService;

    private final CAutobusQueryService cAutobusQueryService;

    public CAutobusResource(CAutobusService cAutobusService, CAutobusQueryService cAutobusQueryService) {
        this.cAutobusService = cAutobusService;
        this.cAutobusQueryService = cAutobusQueryService;
    }

    /**
     * {@code POST  /c-autobuses} : Create a new cAutobus.
     *
     * @param cAutobusDTO the cAutobusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cAutobusDTO, or with status {@code 400 (Bad Request)} if the cAutobus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/c-autobuses")
    public ResponseEntity<CAutobusDTO> createCAutobus(@Valid @RequestBody CAutobusDTO cAutobusDTO) throws URISyntaxException {
        log.debug("REST request to save CAutobus : {}", cAutobusDTO);
        if (cAutobusDTO.getId() != null) {
            throw new BadRequestAlertException("A new cAutobus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CAutobusDTO result = cAutobusService.save(cAutobusDTO);
        return ResponseEntity.created(new URI("/api/c-autobuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /c-autobuses} : Updates an existing cAutobus.
     *
     * @param cAutobusDTO the cAutobusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cAutobusDTO,
     * or with status {@code 400 (Bad Request)} if the cAutobusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cAutobusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/c-autobuses")
    public ResponseEntity<CAutobusDTO> updateCAutobus(@Valid @RequestBody CAutobusDTO cAutobusDTO) throws URISyntaxException {
        log.debug("REST request to update CAutobus : {}", cAutobusDTO);
        if (cAutobusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CAutobusDTO result = cAutobusService.save(cAutobusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cAutobusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /c-autobuses} : get all the cAutobuses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cAutobuses in body.
     */
    @GetMapping("/c-autobuses")
    public ResponseEntity<List<CAutobusDTO>> getAllCAutobuses(CAutobusCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CAutobuses by criteria: {}", criteria);
        Page<CAutobusDTO> page = cAutobusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /c-autobuses/count} : count all the cAutobuses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/c-autobuses/count")
    public ResponseEntity<Long> countCAutobuses(CAutobusCriteria criteria) {
        log.debug("REST request to count CAutobuses by criteria: {}", criteria);
        return ResponseEntity.ok().body(cAutobusQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /c-autobuses/:id} : get the "id" cAutobus.
     *
     * @param id the id of the cAutobusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cAutobusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/c-autobuses/{id}")
    public ResponseEntity<CAutobusDTO> getCAutobus(@PathVariable Long id) {
        log.debug("REST request to get CAutobus : {}", id);
        Optional<CAutobusDTO> cAutobusDTO = cAutobusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cAutobusDTO);
    }

    /**
     * {@code DELETE  /c-autobuses/:id} : delete the "id" cAutobus.
     *
     * @param id the id of the cAutobusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/c-autobuses/{id}")
    public ResponseEntity<Void> deleteCAutobus(@PathVariable Long id) {
        log.debug("REST request to delete CAutobus : {}", id);
        cAutobusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
