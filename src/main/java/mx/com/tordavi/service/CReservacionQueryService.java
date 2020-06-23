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

import mx.com.tordavi.domain.CReservacion;
import mx.com.tordavi.domain.*; // for static metamodels
import mx.com.tordavi.repository.CReservacionRepository;
import mx.com.tordavi.service.dto.CReservacionCriteria;
import mx.com.tordavi.service.dto.CReservacionDTO;
import mx.com.tordavi.service.mapper.CReservacionMapper;

/**
 * Service for executing complex queries for {@link CReservacion} entities in the database.
 * The main input is a {@link CReservacionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CReservacionDTO} or a {@link Page} of {@link CReservacionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CReservacionQueryService extends QueryService<CReservacion> {

    private final Logger log = LoggerFactory.getLogger(CReservacionQueryService.class);

    private final CReservacionRepository cReservacionRepository;

    private final CReservacionMapper cReservacionMapper;

    public CReservacionQueryService(CReservacionRepository cReservacionRepository, CReservacionMapper cReservacionMapper) {
        this.cReservacionRepository = cReservacionRepository;
        this.cReservacionMapper = cReservacionMapper;
    }

    /**
     * Return a {@link List} of {@link CReservacionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CReservacionDTO> findByCriteria(CReservacionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CReservacion> specification = createSpecification(criteria);
        return cReservacionMapper.toDto(cReservacionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CReservacionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CReservacionDTO> findByCriteria(CReservacionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CReservacion> specification = createSpecification(criteria);
        return cReservacionRepository.findAll(specification, page)
            .map(cReservacionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CReservacionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CReservacion> specification = createSpecification(criteria);
        return cReservacionRepository.count(specification);
    }

    /**
     * Function to convert {@link CReservacionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CReservacion> createSpecification(CReservacionCriteria criteria) {
        Specification<CReservacion> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CReservacion_.id));
            }
            if (criteria.getClaveReservacion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClaveReservacion(), CReservacion_.claveReservacion));
            }
            if (criteria.getPrecio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrecio(), CReservacion_.precio));
            }
            if (criteria.getNumPasajeros() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumPasajeros(), CReservacion_.numPasajeros));
            }
            if (criteria.getComentarios() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComentarios(), CReservacion_.comentarios));
            }
            if (criteria.getIdUsuarioCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioCreacion(), CReservacion_.idUsuarioCreacion));
            }
            if (criteria.getFechaCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaCreacion(), CReservacion_.fechaCreacion));
            }
            if (criteria.getIdUsuarioActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioActualizacion(), CReservacion_.idUsuarioActualizacion));
            }
            if (criteria.getFechaActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaActualizacion(), CReservacion_.fechaActualizacion));
            }
            if (criteria.getNotas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotas(), CReservacion_.notas));
            }
            if (criteria.getEstatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstatus(), CReservacion_.estatus));
            }
            if (criteria.getBorrado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBorrado(), CReservacion_.borrado));
            }
            if (criteria.getClienteId() != null) {
                specification = specification.and(buildSpecification(criteria.getClienteId(),
                    root -> root.join(CReservacion_.cliente, JoinType.LEFT).get(CCliente_.id)));
            }
            if (criteria.getPasajeroPrincipalId() != null) {
                specification = specification.and(buildSpecification(criteria.getPasajeroPrincipalId(),
                    root -> root.join(CReservacion_.pasajeroPrincipal, JoinType.LEFT).get(CPasajero_.id)));
            }
            if (criteria.getCorridaId() != null) {
                specification = specification.and(buildSpecification(criteria.getCorridaId(),
                    root -> root.join(CReservacion_.corrida, JoinType.LEFT).get(CCorrida_.id)));
            }
        }
        return specification;
    }
}
