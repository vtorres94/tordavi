package mx.com.tordavi.web.rest;

import mx.com.tordavi.service.CPasajeroService;
import mx.com.tordavi.web.rest.errors.BadRequestAlertException;
import mx.com.tordavi.service.dto.CPasajeroDTO;
import mx.com.tordavi.service.dto.CPasajeroCriteria;
import mx.com.tordavi.service.CPasajeroQueryService;

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
 * REST controller for managing {@link mx.com.tordavi.domain.CPasajero}.
 */

@RestController
@RequestMapping("/api")
public class CPasajeroResource {

    private final Logger log = LoggerFactory.getLogger(CPasajeroResource.class);

    private static final String ENTITY_NAME = "cPasajero";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CPasajeroService pasajeroService;

    private final CPasajeroQueryService pasajeroQueryService;

    public CPasajeroResource(CPasajeroService pasajeroService, CPasajeroQueryService pasajeroQueryService) {
        this.pasajeroService = pasajeroService;
        this.pasajeroQueryService = pasajeroQueryService;
    }

    /**
     * {@code POST  /pasajeros} : Create a new pasajero.
     *
     * @param pasajeroDTO the pasajeroDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pasajeroDTO, or with status {@code 400 (Bad Request)} if the pasajero has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/c-pasajeros")
    public ResponseEntity<CPasajeroDTO> createCPasajero(@Valid @RequestBody CPasajeroDTO pasajeroDTO) throws URISyntaxException {
        log.debug("REST request to save CPasajero : {}", pasajeroDTO);
        if (pasajeroDTO.getId() != null) {
            throw new BadRequestAlertException("A new pasajero cannot already have an ID", ENTITY_NAME, "idnull");
        }
        int resultDuplicate = 0/* pasajeroService.duplicateCreate(pasajeroDTO) */;
        if (resultDuplicate>0){
            throw new BadRequestAlertException("", "duplicate."+ENTITY_NAME, "duplicate");
        }
        else{
            CPasajeroDTO result = pasajeroService.save(pasajeroDTO);
            return ResponseEntity.created(new URI("/api/pasajeros/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
        }
    }

    /**
     * {@code PUT  /pasajeros} : Updates an existing pasajero.
     *
     * @param pasajeroDTO the pasajeroDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pasajeroDTO,
     * or with status {@code 400 (Bad Request)} if the pasajeroDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pasajeroDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/c-pasajeros")
    public ResponseEntity<CPasajeroDTO> updateCPasajero(@Valid @RequestBody CPasajeroDTO pasajeroDTO) throws URISyntaxException {
        log.debug("REST request to update CPasajero : {}", pasajeroDTO);
        if (pasajeroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        int resultDuplicate = 0/* pasajeroService.duplicateUpdate(pasajeroDTO) */;
        if (resultDuplicate>0){
            throw new BadRequestAlertException("", "duplicate."+ENTITY_NAME, "duplicate");
        }
        else{
            CPasajeroDTO result = pasajeroService.save(pasajeroDTO);
            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pasajeroDTO.getId().toString()))
                .body(result);
        }
    }

    /**
     * {@code GET  /c-pasajeros} : get all the pasajeros.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pasajeros in body.
     */
    @GetMapping("/c-pasajeros")
    public ResponseEntity<List<CPasajeroDTO>> getAllCPasajeros(CPasajeroCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CPasajeros by criteria: {}", criteria);
        Page<CPasajeroDTO> page = pasajeroQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /pasajeros/count} : count all the pasajeros.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/c-pasajeros/count")
    public ResponseEntity<Long> countCPasajeros(CPasajeroCriteria criteria) {
        log.debug("REST request to count CPasajeros by criteria: {}", criteria);
        return ResponseEntity.ok().body(pasajeroQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pasajeros/:id} : get the "id" pasajero.
     *
     * @param id the id of the pasajeroDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pasajeroDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/c-pasajeros/{id}")
    public ResponseEntity<CPasajeroDTO> getCPasajero(@PathVariable Long id) {
        log.debug("REST request to get CPasajero : {}", id);
        Optional<CPasajeroDTO> pasajeroDTO = pasajeroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pasajeroDTO);
    }

    /**
     * {@code DELETE  /pasajeros/:id} : delete the "id" pasajero.
     *
     * @param id the id of the pasajeroDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/c-pasajeros/{id}")
    public ResponseEntity<Void> deleteCPasajero(@PathVariable Long id) {
        log.debug("REST request to delete CPasajero : {}", id);
        pasajeroService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    /**
     * {@code PUT  /pacientes} : Updates an existing cEstado.
     *
     * @param pasajeroDTO the pasajeroDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pasajeroDTO,
     * or with status {@code 400 (Bad Request)} if the pasajeroDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pasajeroDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/c-pasajeros/logic")
    public ResponseEntity<CPasajeroDTO> logicDeleteCPasajero(@Valid @RequestBody CPasajeroDTO pasajeroDTO) throws URISyntaxException {
        log.debug("REST request to save CPasajero : {}", pasajeroDTO);
        if (pasajeroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CPasajeroDTO result = pasajeroService.save(pasajeroDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, pasajeroDTO.getId().toString()))
            .body(result);
    }

    @PutMapping("/c-pasajeros/borrar")
    public ResponseEntity<CPasajeroDTO> updateCPasajeroDelete(@Valid @RequestBody CPasajeroDTO pasajeroDTO) throws URISyntaxException {
        log.debug("REST request to update CPasajero : {}", pasajeroDTO);
        if (pasajeroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        pasajeroDTO.setBorrado(1);
        pasajeroDTO.setEstatus(0);
        CPasajeroDTO result = pasajeroService.save(pasajeroDTO);
        
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, pasajeroDTO.getId().toString()))
            .body(result);
    }
/*
    @GetMapping("/pasajeros/Activos")
    public List<CPasajeroDTO> getAllCPasajerosActivos() {
        log.debug("REST request to get all CPasajeros");
        return pasajeroService.findAllActivos();
    }
*/

}
