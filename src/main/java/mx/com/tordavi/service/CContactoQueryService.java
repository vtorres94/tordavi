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

import mx.com.tordavi.domain.CContacto;
import mx.com.tordavi.domain.*; // for static metamodels
import mx.com.tordavi.repository.CContactoRepository;
import mx.com.tordavi.service.dto.CContactoCriteria;
import mx.com.tordavi.service.dto.CContactoDTO;
import mx.com.tordavi.service.mapper.CContactoMapper;

/**
 * Service for executing complex queries for {@link CContacto} entities in the database.
 * The main input is a {@link CContactoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CContactoDTO} or a {@link Page} of {@link CContactoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CContactoQueryService extends QueryService<CContacto> {

    private final Logger log = LoggerFactory.getLogger(CContactoQueryService.class);

    private final CContactoRepository cContactoRepository;

    private final CContactoMapper cContactoMapper;

    public CContactoQueryService(CContactoRepository cContactoRepository, CContactoMapper cContactoMapper) {
        this.cContactoRepository = cContactoRepository;
        this.cContactoMapper = cContactoMapper;
    }

    /**
     * Return a {@link List} of {@link CContactoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CContactoDTO> findByCriteria(CContactoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CContacto> specification = createSpecification(criteria);
        return cContactoMapper.toDto(cContactoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CContactoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CContactoDTO> findByCriteria(CContactoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CContacto> specification = createSpecification(criteria);
        return cContactoRepository.findAll(specification, page)
            .map(cContactoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CContactoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CContacto> specification = createSpecification(criteria);
        return cContactoRepository.count(specification);
    }

    /**
     * Function to convert {@link CContactoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CContacto> createSpecification(CContactoCriteria criteria) {
        Specification<CContacto> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CContacto_.id));
            }
            if (criteria.getNombreContacto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombreContacto(), CContacto_.nombreContacto));
            }
            if (criteria.getTelefono() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefono(), CContacto_.telefono));
            }
            if (criteria.getArea() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArea(), CContacto_.area));
            }
            if (criteria.getIdUsuarioCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioCreacion(), CContacto_.idUsuarioCreacion));
            }
            if (criteria.getFechaCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaCreacion(), CContacto_.fechaCreacion));
            }
            if (criteria.getIdUsuarioActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioActualizacion(), CContacto_.idUsuarioActualizacion));
            }
            if (criteria.getFechaActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaActualizacion(), CContacto_.fechaActualizacion));
            }
            if (criteria.getNotas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotas(), CContacto_.notas));
            }
            if (criteria.getEstatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstatus(), CContacto_.estatus));
            }
            if (criteria.getBorrado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBorrado(), CContacto_.borrado));
            }
            if (criteria.getClienteId() != null) {
                specification = specification.and(buildSpecification(criteria.getClienteId(),
                    root -> root.join(CContacto_.cliente, JoinType.LEFT).get(CCliente_.id)));
            }
            if (criteria.getPasajeroId() != null) {
                specification = specification.and(buildSpecification(criteria.getPasajeroId(),
                    root -> root.join(CContacto_.pasajero, JoinType.LEFT).get(CPasajero_.id)));
            }
        }
        return specification;
    }
}
