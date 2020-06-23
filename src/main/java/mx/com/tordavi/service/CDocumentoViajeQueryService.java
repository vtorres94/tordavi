package mx.com.tordavi.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import mx.com.tordavi.domain.CDocumentoViaje;
import mx.com.tordavi.domain.*; // for static metamodels
import mx.com.tordavi.repository.CDocumentoViajeRepository;
import mx.com.tordavi.service.dto.CDocumentoViajeCriteria;
import mx.com.tordavi.service.dto.CDocumentoViajeDTO;
import mx.com.tordavi.service.mapper.CDocumentoViajeMapper;

/**
 * Service for executing complex queries for {@link CDocumentoViaje} entities in the database.
 * The main input is a {@link CDocumentoViajeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CDocumentoViajeDTO} or a {@link Page} of {@link CDocumentoViajeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CDocumentoViajeQueryService extends QueryService<CDocumentoViaje> {

    private final Logger log = LoggerFactory.getLogger(CDocumentoViajeQueryService.class);

    private final CDocumentoViajeRepository cDocumentoViajeRepository;

    private final CDocumentoViajeMapper cDocumentoViajeMapper;

    public CDocumentoViajeQueryService(CDocumentoViajeRepository cDocumentoViajeRepository, CDocumentoViajeMapper cDocumentoViajeMapper) {
        this.cDocumentoViajeRepository = cDocumentoViajeRepository;
        this.cDocumentoViajeMapper = cDocumentoViajeMapper;
    }

    /**
     * Return a {@link List} of {@link CDocumentoViajeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CDocumentoViajeDTO> findByCriteria(CDocumentoViajeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CDocumentoViaje> specification = createSpecification(criteria);
        return cDocumentoViajeMapper.toDto(cDocumentoViajeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CDocumentoViajeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CDocumentoViajeDTO> findByCriteria(CDocumentoViajeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CDocumentoViaje> specification = createSpecification(criteria);
        return cDocumentoViajeRepository.findAll(specification, page)
            .map(cDocumentoViajeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CDocumentoViajeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CDocumentoViaje> specification = createSpecification(criteria);
        return cDocumentoViajeRepository.count(specification);
    }

    /**
     * Function to convert {@link CDocumentoViajeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CDocumentoViaje> createSpecification(CDocumentoViajeCriteria criteria) {
        Specification<CDocumentoViaje> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CDocumentoViaje_.id));
            }
            if (criteria.getClaveDocumento() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClaveDocumento(), CDocumentoViaje_.claveDocumento));
            }
            if (criteria.getDocumento() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDocumento(), CDocumentoViaje_.documento));
            }
            if (criteria.getIdUsuarioCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioCreacion(), CDocumentoViaje_.idUsuarioCreacion));
            }
            if (criteria.getFechaCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaCreacion(), CDocumentoViaje_.fechaCreacion));
            }
            if (criteria.getIdUsuarioActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioActualizacion(), CDocumentoViaje_.idUsuarioActualizacion));
            }
            if (criteria.getFechaActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaActualizacion(), CDocumentoViaje_.fechaActualizacion));
            }
            if (criteria.getNotas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotas(), CDocumentoViaje_.notas));
            }
            if (criteria.getEstatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstatus(), CDocumentoViaje_.estatus));
            }
            if (criteria.getBorrado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBorrado(), CDocumentoViaje_.borrado));
            }
            if (criteria.getClienteId() != null) {
                specification = specification.and(buildSpecification(criteria.getClienteId(),
                    root -> root.join(CDocumentoViaje_.cliente, JoinType.LEFT).get(CCliente_.id)));
            }
            if (criteria.getPasajeroId() != null) {
                specification = specification.and(buildSpecification(criteria.getPasajeroId(),
                    root -> root.join(CDocumentoViaje_.pasajero, JoinType.LEFT).get(CPasajero_.id)));
            }
        }
        return specification;
    }
}
