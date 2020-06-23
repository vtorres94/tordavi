package mx.com.tordavi.web.rest;

import mx.com.tordavi.service.CClienteService;
import mx.com.tordavi.web.rest.errors.BadRequestAlertException;
import mx.com.tordavi.service.dto.CClienteDTO;
import mx.com.tordavi.service.dto.CClienteCriteria;
import mx.com.tordavi.service.CClienteQueryService;

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
 * REST controller for managing {@link mx.com.tordavi.domain.CCliente}.
 */
@RestController
@RequestMapping("/api")
public class CClienteResource {

    private final Logger log = LoggerFactory.getLogger(CClienteResource.class);

    private static final String ENTITY_NAME = "cCliente";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CClienteService cClienteService;

    private final CClienteQueryService cClienteQueryService;

    public CClienteResource(CClienteService cClienteService, CClienteQueryService cClienteQueryService) {
        this.cClienteService = cClienteService;
        this.cClienteQueryService = cClienteQueryService;
    }

    /**
     * {@code POST  /c-clientes} : Create a new cCliente.
     *
     * @param cClienteDTO the cClienteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cClienteDTO, or with status {@code 400 (Bad Request)} if the cCliente has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/c-clientes")
    public ResponseEntity<CClienteDTO> createCCliente(@Valid @RequestBody CClienteDTO cClienteDTO) throws URISyntaxException {
        log.debug("REST request to save CCliente : {}", cClienteDTO);
        if (cClienteDTO.getId() != null) {
            throw new BadRequestAlertException("A new cCliente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CClienteDTO result = cClienteService.save(cClienteDTO);
        return ResponseEntity.created(new URI("/api/c-clientes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /c-clientes} : Updates an existing cCliente.
     *
     * @param cClienteDTO the cClienteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cClienteDTO,
     * or with status {@code 400 (Bad Request)} if the cClienteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cClienteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/c-clientes")
    public ResponseEntity<CClienteDTO> updateCCliente(@Valid @RequestBody CClienteDTO cClienteDTO) throws URISyntaxException {
        log.debug("REST request to update CCliente : {}", cClienteDTO);
        if (cClienteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CClienteDTO result = cClienteService.save(cClienteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cClienteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /c-clientes} : get all the cClientes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cClientes in body.
     */
    @GetMapping("/c-clientes")
    public ResponseEntity<List<CClienteDTO>> getAllCClientes(CClienteCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CClientes by criteria: {}", criteria);
        Page<CClienteDTO> page = cClienteQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /c-clientes/count} : count all the cClientes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/c-clientes/count")
    public ResponseEntity<Long> countCClientes(CClienteCriteria criteria) {
        log.debug("REST request to count CClientes by criteria: {}", criteria);
        return ResponseEntity.ok().body(cClienteQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /c-clientes/:id} : get the "id" cCliente.
     *
     * @param id the id of the cClienteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cClienteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/c-clientes/{id}")
    public ResponseEntity<CClienteDTO> getCCliente(@PathVariable Long id) {
        log.debug("REST request to get CCliente : {}", id);
        Optional<CClienteDTO> cClienteDTO = cClienteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cClienteDTO);
    }

    /**
     * {@code DELETE  /c-clientes/:id} : delete the "id" cCliente.
     *
     * @param id the id of the cClienteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/c-clientes/{id}")
    public ResponseEntity<Void> deleteCCliente(@PathVariable Long id) {
        log.debug("REST request to delete CCliente : {}", id);
        cClienteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
