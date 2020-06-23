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

import mx.com.tordavi.domain.CPendiente;
import mx.com.tordavi.domain.*; // for static metamodels
import mx.com.tordavi.repository.CPendienteRepository;
import mx.com.tordavi.service.dto.CPendienteCriteria;
import mx.com.tordavi.service.dto.CPendienteDTO;
import mx.com.tordavi.service.mapper.CPendienteMapper;

/**
 * Service for executing complex queries for {@link CPendiente} entities in the database.
 * The main input is a {@link CPendienteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CPendienteDTO} or a {@link Page} of {@link CPendienteDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CPendienteQueryService extends QueryService<CPendiente> {

    private final Logger log = LoggerFactory.getLogger(CPendienteQueryService.class);

    private final CPendienteRepository cPendienteRepository;

    private final CPendienteMapper cPendienteMapper;

    public CPendienteQueryService(CPendienteRepository cPendienteRepository, CPendienteMapper cPendienteMapper) {
        this.cPendienteRepository = cPendienteRepository;
        this.cPendienteMapper = cPendienteMapper;
    }

    /**
     * Return a {@link List} of {@link CPendienteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CPendienteDTO> findByCriteria(CPendienteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CPendiente> specification = createSpecification(criteria);
        return cPendienteMapper.toDto(cPendienteRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CPendienteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CPendienteDTO> findByCriteria(CPendienteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CPendiente> specification = createSpecification(criteria);
        return cPendienteRepository.findAll(specification, page)
            .map(cPendienteMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CPendienteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CPendiente> specification = createSpecification(criteria);
        return cPendienteRepository.count(specification);
    }

    /**
     * Function to convert {@link CPendienteCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CPendiente> createSpecification(CPendienteCriteria criteria) {
        Specification<CPendiente> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CPendiente_.id));
            }
            if (criteria.getComentarios() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComentarios(), CPendiente_.comentarios));
            }
            if (criteria.getIdUsuarioCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioCreacion(), CPendiente_.idUsuarioCreacion));
            }
            if (criteria.getFechaCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaCreacion(), CPendiente_.fechaCreacion));
            }
            if (criteria.getIdUsuarioActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioActualizacion(), CPendiente_.idUsuarioActualizacion));
            }
            if (criteria.getFechaActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaActualizacion(), CPendiente_.fechaActualizacion));
            }
            if (criteria.getNotas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotas(), CPendiente_.notas));
            }
            if (criteria.getEstatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstatus(), CPendiente_.estatus));
            }
            if (criteria.getBorrado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBorrado(), CPendiente_.borrado));
            }
            if (criteria.getClienteId() != null) {
                specification = specification.and(buildSpecification(criteria.getClienteId(),
                    root -> root.join(CPendiente_.cliente, JoinType.LEFT).get(CCliente_.id)));
            }
            if (criteria.getReservacionId() != null) {
                specification = specification.and(buildSpecification(criteria.getReservacionId(),
                    root -> root.join(CPendiente_.reservacion, JoinType.LEFT).get(CReservacion_.id)));
            }
        }
        return specification;
    }
}
