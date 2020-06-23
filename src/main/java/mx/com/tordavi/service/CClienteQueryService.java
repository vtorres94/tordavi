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

import mx.com.tordavi.domain.CCliente;
import mx.com.tordavi.domain.*; // for static metamodels
import mx.com.tordavi.repository.CClienteRepository;
import mx.com.tordavi.service.dto.CClienteCriteria;
import mx.com.tordavi.service.dto.CClienteDTO;
import mx.com.tordavi.service.mapper.CClienteMapper;

/**
 * Service for executing complex queries for {@link CCliente} entities in the database.
 * The main input is a {@link CClienteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CClienteDTO} or a {@link Page} of {@link CClienteDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CClienteQueryService extends QueryService<CCliente> {

    private final Logger log = LoggerFactory.getLogger(CClienteQueryService.class);

    private final CClienteRepository cClienteRepository;

    private final CClienteMapper cClienteMapper;

    public CClienteQueryService(CClienteRepository cClienteRepository, CClienteMapper cClienteMapper) {
        this.cClienteRepository = cClienteRepository;
        this.cClienteMapper = cClienteMapper;
    }

    /**
     * Return a {@link List} of {@link CClienteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CClienteDTO> findByCriteria(CClienteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CCliente> specification = createSpecification(criteria);
        return cClienteMapper.toDto(cClienteRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CClienteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CClienteDTO> findByCriteria(CClienteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CCliente> specification = createSpecification(criteria);
        return cClienteRepository.findAll(specification, page)
            .map(cClienteMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CClienteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CCliente> specification = createSpecification(criteria);
        return cClienteRepository.count(specification);
    }

    /**
     * Function to convert {@link CClienteCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CCliente> createSpecification(CClienteCriteria criteria) {
        Specification<CCliente> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CCliente_.id));
            }
            if (criteria.getClaveCliente() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClaveCliente(), CCliente_.claveCliente));
            }
            if (criteria.getCliente() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCliente(), CCliente_.cliente));
            }
            if (criteria.getLogo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLogo(), CCliente_.logo));
            }
            if (criteria.getIdUsuarioCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioCreacion(), CCliente_.idUsuarioCreacion));
            }
            if (criteria.getFechaCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaCreacion(), CCliente_.fechaCreacion));
            }
            if (criteria.getIdUsuarioActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioActualizacion(), CCliente_.idUsuarioActualizacion));
            }
            if (criteria.getFechaActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaActualizacion(), CCliente_.fechaActualizacion));
            }
            if (criteria.getNotas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotas(), CCliente_.notas));
            }
            if (criteria.getEstatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstatus(), CCliente_.estatus));
            }
            if (criteria.getBorrado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBorrado(), CCliente_.borrado));
            }
        }
        return specification;
    }
}
