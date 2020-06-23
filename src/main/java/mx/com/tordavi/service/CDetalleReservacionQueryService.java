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

import mx.com.tordavi.domain.CDetalleReservacion;
import mx.com.tordavi.domain.*; // for static metamodels
import mx.com.tordavi.repository.CDetalleReservacionRepository;
import mx.com.tordavi.service.dto.CDetalleReservacionCriteria;
import mx.com.tordavi.service.dto.CDetalleReservacionDTO;
import mx.com.tordavi.service.mapper.CDetalleReservacionMapper;

/**
 * Service for executing complex queries for {@link CDetalleReservacion} entities in the database.
 * The main input is a {@link CDetalleReservacionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CDetalleReservacionDTO} or a {@link Page} of {@link CDetalleReservacionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CDetalleReservacionQueryService extends QueryService<CDetalleReservacion> {

    private final Logger log = LoggerFactory.getLogger(CDetalleReservacionQueryService.class);

    private final CDetalleReservacionRepository cDetalleReservacionRepository;

    private final CDetalleReservacionMapper cDetalleReservacionMapper;

    public CDetalleReservacionQueryService(CDetalleReservacionRepository cDetalleReservacionRepository, CDetalleReservacionMapper cDetalleReservacionMapper) {
        this.cDetalleReservacionRepository = cDetalleReservacionRepository;
        this.cDetalleReservacionMapper = cDetalleReservacionMapper;
    }

    /**
     * Return a {@link List} of {@link CDetalleReservacionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CDetalleReservacionDTO> findByCriteria(CDetalleReservacionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CDetalleReservacion> specification = createSpecification(criteria);
        return cDetalleReservacionMapper.toDto(cDetalleReservacionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CDetalleReservacionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CDetalleReservacionDTO> findByCriteria(CDetalleReservacionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CDetalleReservacion> specification = createSpecification(criteria);
        return cDetalleReservacionRepository.findAll(specification, page)
            .map(cDetalleReservacionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CDetalleReservacionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CDetalleReservacion> specification = createSpecification(criteria);
        return cDetalleReservacionRepository.count(specification);
    }

    /**
     * Function to convert {@link CDetalleReservacionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CDetalleReservacion> createSpecification(CDetalleReservacionCriteria criteria) {
        Specification<CDetalleReservacion> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CDetalleReservacion_.id));
            }
            if (criteria.getPrecio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrecio(), CDetalleReservacion_.precio));
            }
            if (criteria.getIdUsuarioCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioCreacion(), CDetalleReservacion_.idUsuarioCreacion));
            }
            if (criteria.getFechaCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaCreacion(), CDetalleReservacion_.fechaCreacion));
            }
            if (criteria.getIdUsuarioActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioActualizacion(), CDetalleReservacion_.idUsuarioActualizacion));
            }
            if (criteria.getFechaActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaActualizacion(), CDetalleReservacion_.fechaActualizacion));
            }
            if (criteria.getNotas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotas(), CDetalleReservacion_.notas));
            }
            if (criteria.getEstatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstatus(), CDetalleReservacion_.estatus));
            }
            if (criteria.getBorrado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBorrado(), CDetalleReservacion_.borrado));
            }
            if (criteria.getClienteId() != null) {
                specification = specification.and(buildSpecification(criteria.getClienteId(),
                    root -> root.join(CDetalleReservacion_.cliente, JoinType.LEFT).get(CCliente_.id)));
            }
            if (criteria.getPasajeroId() != null) {
                specification = specification.and(buildSpecification(criteria.getPasajeroId(),
                    root -> root.join(CDetalleReservacion_.pasajero, JoinType.LEFT).get(CPasajero_.id)));
            }
            if (criteria.getReservacionId() != null) {
                specification = specification.and(buildSpecification(criteria.getReservacionId(),
                    root -> root.join(CDetalleReservacion_.reservacion, JoinType.LEFT).get(CReservacion_.id)));
            }
        }
        return specification;
    }
}
