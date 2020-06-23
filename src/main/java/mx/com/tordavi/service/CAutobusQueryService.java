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

import mx.com.tordavi.domain.CAutobus;
import mx.com.tordavi.domain.*; // for static metamodels
import mx.com.tordavi.repository.CAutobusRepository;
import mx.com.tordavi.service.dto.CAutobusCriteria;
import mx.com.tordavi.service.dto.CAutobusDTO;
import mx.com.tordavi.service.mapper.CAutobusMapper;

/**
 * Service for executing complex queries for {@link CAutobus} entities in the database.
 * The main input is a {@link CAutobusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CAutobusDTO} or a {@link Page} of {@link CAutobusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CAutobusQueryService extends QueryService<CAutobus> {

    private final Logger log = LoggerFactory.getLogger(CAutobusQueryService.class);

    private final CAutobusRepository cAutobusRepository;

    private final CAutobusMapper cAutobusMapper;

    public CAutobusQueryService(CAutobusRepository cAutobusRepository, CAutobusMapper cAutobusMapper) {
        this.cAutobusRepository = cAutobusRepository;
        this.cAutobusMapper = cAutobusMapper;
    }

    /**
     * Return a {@link List} of {@link CAutobusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CAutobusDTO> findByCriteria(CAutobusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CAutobus> specification = createSpecification(criteria);
        return cAutobusMapper.toDto(cAutobusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CAutobusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CAutobusDTO> findByCriteria(CAutobusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CAutobus> specification = createSpecification(criteria);
        return cAutobusRepository.findAll(specification, page)
            .map(cAutobusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CAutobusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CAutobus> specification = createSpecification(criteria);
        return cAutobusRepository.count(specification);
    }

    /**
     * Function to convert {@link CAutobusCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CAutobus> createSpecification(CAutobusCriteria criteria) {
        Specification<CAutobus> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CAutobus_.id));
            }
            if (criteria.getAutobus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAutobus(), CAutobus_.autobus));
            }
            if (criteria.getIdUsuarioCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioCreacion(), CAutobus_.idUsuarioCreacion));
            }
            if (criteria.getFechaCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaCreacion(), CAutobus_.fechaCreacion));
            }
            if (criteria.getIdUsuarioActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioActualizacion(), CAutobus_.idUsuarioActualizacion));
            }
            if (criteria.getFechaActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaActualizacion(), CAutobus_.fechaActualizacion));
            }
            if (criteria.getNotas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotas(), CAutobus_.notas));
            }
            if (criteria.getEstatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstatus(), CAutobus_.estatus));
            }
            if (criteria.getBorrado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBorrado(), CAutobus_.borrado));
            }
            if (criteria.getClienteId() != null) {
                specification = specification.and(buildSpecification(criteria.getClienteId(),
                    root -> root.join(CAutobus_.cliente, JoinType.LEFT).get(CCliente_.id)));
            }
        }
        return specification;
    }
}
