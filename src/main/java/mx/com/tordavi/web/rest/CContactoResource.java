package mx.com.tordavi.web.rest;

import mx.com.tordavi.service.CContactoService;
import mx.com.tordavi.web.rest.errors.BadRequestAlertException;
import mx.com.tordavi.service.dto.CContactoDTO;
import mx.com.tordavi.service.dto.CContactoCriteria;
import mx.com.tordavi.service.CContactoQueryService;

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
 * REST controller for managing {@link mx.com.tordavi.domain.CContacto}.
 */
@RestController
@RequestMapping("/api")
public class CContactoResource {

    private final Logger log = LoggerFactory.getLogger(CContactoResource.class);

    private static final String ENTITY_NAME = "cContacto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CContactoService cContactoService;

    private final CContactoQueryService cContactoQueryService;

    public CContactoResource(CContactoService cContactoService, CContactoQueryService cContactoQueryService) {
        this.cContactoService = cContactoService;
        this.cContactoQueryService = cContactoQueryService;
    }

    /**
     * {@code POST  /c-contactos} : Create a new cContacto.
     *
     * @param cContactoDTO the cContactoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cContactoDTO, or with status {@code 400 (Bad Request)} if the cContacto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/c-contactos")
    public ResponseEntity<CContactoDTO> createCContacto(@Valid @RequestBody CContactoDTO cContactoDTO) throws URISyntaxException {
        log.debug("REST request to save CContacto : {}", cContactoDTO);
        if (cContactoDTO.getId() != null) {
            throw new BadRequestAlertException("A new cContacto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CContactoDTO result = cContactoService.save(cContactoDTO);
        return ResponseEntity.created(new URI("/api/c-contactos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /c-contactos} : Updates an existing cContacto.
     *
     * @param cContactoDTO the cContactoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cContactoDTO,
     * or with status {@code 400 (Bad Request)} if the cContactoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cContactoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/c-contactos")
    public ResponseEntity<CContactoDTO> updateCContacto(@Valid @RequestBody CContactoDTO cContactoDTO) throws URISyntaxException {
        log.debug("REST request to update CContacto : {}", cContactoDTO);
        if (cContactoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CContactoDTO result = cContactoService.save(cContactoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cContactoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /c-contactos} : get all the cContactos.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cContactos in body.
     */
    @GetMapping("/c-contactos")
    public ResponseEntity<List<CContactoDTO>> getAllCContactos(CContactoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CContactos by criteria: {}", criteria);
        Page<CContactoDTO> page = cContactoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /c-contactos/count} : count all the cContactos.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/c-contactos/count")
    public ResponseEntity<Long> countCContactos(CContactoCriteria criteria) {
        log.debug("REST request to count CContactos by criteria: {}", criteria);
        return ResponseEntity.ok().body(cContactoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /c-contactos/:id} : get the "id" cContacto.
     *
     * @param id the id of the cContactoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cContactoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/c-contactos/{id}")
    public ResponseEntity<CContactoDTO> getCContacto(@PathVariable Long id) {
        log.debug("REST request to get CContacto : {}", id);
        Optional<CContactoDTO> cContactoDTO = cContactoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cContactoDTO);
    }

    /**
     * {@code DELETE  /c-contactos/:id} : delete the "id" cContacto.
     *
     * @param id the id of the cContactoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/c-contactos/{id}")
    public ResponseEntity<Void> deleteCContacto(@PathVariable Long id) {
        log.debug("REST request to delete CContacto : {}", id);
        cContactoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
