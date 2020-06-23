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

import mx.com.tordavi.domain.CVenta;
import mx.com.tordavi.domain.*; // for static metamodels
import mx.com.tordavi.repository.CVentaRepository;
import mx.com.tordavi.service.dto.CVentaCriteria;
import mx.com.tordavi.service.dto.CVentaDTO;
import mx.com.tordavi.service.mapper.CVentaMapper;

/**
 * Service for executing complex queries for {@link CVenta} entities in the database.
 * The main input is a {@link CVentaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CVentaDTO} or a {@link Page} of {@link CVentaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CVentaQueryService extends QueryService<CVenta> {

    private final Logger log = LoggerFactory.getLogger(CVentaQueryService.class);

    private final CVentaRepository cVentaRepository;

    private final CVentaMapper cVentaMapper;

    public CVentaQueryService(CVentaRepository cVentaRepository, CVentaMapper cVentaMapper) {
        this.cVentaRepository = cVentaRepository;
        this.cVentaMapper = cVentaMapper;
    }

    /**
     * Return a {@link List} of {@link CVentaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CVentaDTO> findByCriteria(CVentaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CVenta> specification = createSpecification(criteria);
        return cVentaMapper.toDto(cVentaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CVentaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CVentaDTO> findByCriteria(CVentaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CVenta> specification = createSpecification(criteria);
        return cVentaRepository.findAll(specification, page)
            .map(cVentaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CVentaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CVenta> specification = createSpecification(criteria);
        return cVentaRepository.count(specification);
    }

    /**
     * Function to convert {@link CVentaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CVenta> createSpecification(CVentaCriteria criteria) {
        Specification<CVenta> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CVenta_.id));
            }
            if (criteria.getVendedorId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVendedorId(), CVenta_.vendedorId));
            }
            if (criteria.getPrecioTotal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrecioTotal(), CVenta_.precioTotal));
            }
            if (criteria.getFechaVenta() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaVenta(), CVenta_.fechaVenta));
            }
            if (criteria.getIdUsuarioCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioCreacion(), CVenta_.idUsuarioCreacion));
            }
            if (criteria.getFechaCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaCreacion(), CVenta_.fechaCreacion));
            }
            if (criteria.getIdUsuarioActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioActualizacion(), CVenta_.idUsuarioActualizacion));
            }
            if (criteria.getFechaActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaActualizacion(), CVenta_.fechaActualizacion));
            }
            if (criteria.getNotas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotas(), CVenta_.notas));
            }
            if (criteria.getEstatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstatus(), CVenta_.estatus));
            }
            if (criteria.getBorrado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBorrado(), CVenta_.borrado));
            }
            if (criteria.getClienteId() != null) {
                specification = specification.and(buildSpecification(criteria.getClienteId(),
                    root -> root.join(CVenta_.cliente, JoinType.LEFT).get(CCliente_.id)));
            }
            if (criteria.getReservacionId() != null) {
                specification = specification.and(buildSpecification(criteria.getReservacionId(),
                    root -> root.join(CVenta_.reservacion, JoinType.LEFT).get(CReservacion_.id)));
            }
        }
        return specification;
    }
}
