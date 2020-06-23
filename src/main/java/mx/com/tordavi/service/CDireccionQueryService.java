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

import mx.com.tordavi.domain.CDireccion;
import mx.com.tordavi.domain.*; // for static metamodels
import mx.com.tordavi.repository.CDireccionRepository;
import mx.com.tordavi.service.dto.CDireccionCriteria;
import mx.com.tordavi.service.dto.CDireccionDTO;
import mx.com.tordavi.service.mapper.CDireccionMapper;

/**
 * Service for executing complex queries for {@link CDireccion} entities in the database.
 * The main input is a {@link CDireccionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CDireccionDTO} or a {@link Page} of {@link CDireccionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CDireccionQueryService extends QueryService<CDireccion> {

    private final Logger log = LoggerFactory.getLogger(CDireccionQueryService.class);

    private final CDireccionRepository cDireccionRepository;

    private final CDireccionMapper cDireccionMapper;

    public CDireccionQueryService(CDireccionRepository cDireccionRepository, CDireccionMapper cDireccionMapper) {
        this.cDireccionRepository = cDireccionRepository;
        this.cDireccionMapper = cDireccionMapper;
    }

    /**
     * Return a {@link List} of {@link CDireccionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CDireccionDTO> findByCriteria(CDireccionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CDireccion> specification = createSpecification(criteria);
        return cDireccionMapper.toDto(cDireccionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CDireccionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CDireccionDTO> findByCriteria(CDireccionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CDireccion> specification = createSpecification(criteria);
        return cDireccionRepository.findAll(specification, page)
            .map(cDireccionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CDireccionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CDireccion> specification = createSpecification(criteria);
        return cDireccionRepository.count(specification);
    }

    /**
     * Function to convert {@link CDireccionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CDireccion> createSpecification(CDireccionCriteria criteria) {
        Specification<CDireccion> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CDireccion_.id));
            }
            if (criteria.getDireccionComplete() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDireccionComplete(), CDireccion_.direccionComplete));
            }
            if (criteria.getDireccion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDireccion(), CDireccion_.direccion));
            }
            if (criteria.getNumExterior() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumExterior(), CDireccion_.numExterior));
            }
            if (criteria.getNumInterior() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumInterior(), CDireccion_.numInterior));
            }
            if (criteria.getCodigoPostal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCodigoPostal(), CDireccion_.codigoPostal));
            }
            if (criteria.getCiudad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCiudad(), CDireccion_.ciudad));
            }
            if (criteria.getEstado() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEstado(), CDireccion_.estado));
            }
            if (criteria.getPais() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPais(), CDireccion_.pais));
            }
            if (criteria.getIdUsuarioCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioCreacion(), CDireccion_.idUsuarioCreacion));
            }
            if (criteria.getFechaCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaCreacion(), CDireccion_.fechaCreacion));
            }
            if (criteria.getIdUsuarioActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioActualizacion(), CDireccion_.idUsuarioActualizacion));
            }
            if (criteria.getFechaActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaActualizacion(), CDireccion_.fechaActualizacion));
            }
            if (criteria.getNotas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotas(), CDireccion_.notas));
            }
            if (criteria.getEstatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstatus(), CDireccion_.estatus));
            }
            if (criteria.getBorrado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBorrado(), CDireccion_.borrado));
            }
            if (criteria.getClienteId() != null) {
                specification = specification.and(buildSpecification(criteria.getClienteId(),
                    root -> root.join(CDireccion_.cliente, JoinType.LEFT).get(CCliente_.id)));
            }
            if (criteria.getPasajeroId() != null) {
                specification = specification.and(buildSpecification(criteria.getPasajeroId(),
                    root -> root.join(CDireccion_.pasajero, JoinType.LEFT).get(CPasajero_.id)));
            }
        }
        return specification;
    }
}
