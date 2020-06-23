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

import mx.com.tordavi.domain.CLugarParada;
import mx.com.tordavi.domain.*; // for static metamodels
import mx.com.tordavi.repository.CLugarParadaRepository;
import mx.com.tordavi.service.dto.CLugarParadaCriteria;
import mx.com.tordavi.service.dto.CLugarParadaDTO;
import mx.com.tordavi.service.mapper.CLugarParadaMapper;

/**
 * Service for executing complex queries for {@link CLugarParada} entities in the database.
 * The main input is a {@link CLugarParadaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CLugarParadaDTO} or a {@link Page} of {@link CLugarParadaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CLugarParadaQueryService extends QueryService<CLugarParada> {

    private final Logger log = LoggerFactory.getLogger(CLugarParadaQueryService.class);

    private final CLugarParadaRepository cLugarParadaRepository;

    private final CLugarParadaMapper cLugarParadaMapper;

    public CLugarParadaQueryService(CLugarParadaRepository cLugarParadaRepository, CLugarParadaMapper cLugarParadaMapper) {
        this.cLugarParadaRepository = cLugarParadaRepository;
        this.cLugarParadaMapper = cLugarParadaMapper;
    }

    /**
     * Return a {@link List} of {@link CLugarParadaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CLugarParadaDTO> findByCriteria(CLugarParadaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CLugarParada> specification = createSpecification(criteria);
        return cLugarParadaMapper.toDto(cLugarParadaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CLugarParadaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CLugarParadaDTO> findByCriteria(CLugarParadaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CLugarParada> specification = createSpecification(criteria);
        return cLugarParadaRepository.findAll(specification, page)
            .map(cLugarParadaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CLugarParadaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CLugarParada> specification = createSpecification(criteria);
        return cLugarParadaRepository.count(specification);
    }

    /**
     * Function to convert {@link CLugarParadaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CLugarParada> createSpecification(CLugarParadaCriteria criteria) {
        Specification<CLugarParada> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CLugarParada_.id));
            }
            if (criteria.getClaveLugarParada() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClaveLugarParada(), CLugarParada_.claveLugarParada));
            }
            if (criteria.getComunidad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComunidad(), CLugarParada_.comunidad));
            }
            if (criteria.getCiudad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCiudad(), CLugarParada_.ciudad));
            }
            if (criteria.getEstado() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEstado(), CLugarParada_.estado));
            }
            if (criteria.getPais() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPais(), CLugarParada_.pais));
            }
            if (criteria.getIdUsuarioCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioCreacion(), CLugarParada_.idUsuarioCreacion));
            }
            if (criteria.getFechaCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaCreacion(), CLugarParada_.fechaCreacion));
            }
            if (criteria.getIdUsuarioActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioActualizacion(), CLugarParada_.idUsuarioActualizacion));
            }
            if (criteria.getFechaActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaActualizacion(), CLugarParada_.fechaActualizacion));
            }
            if (criteria.getNotas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotas(), CLugarParada_.notas));
            }
            if (criteria.getEstatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstatus(), CLugarParada_.estatus));
            }
            if (criteria.getBorrado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBorrado(), CLugarParada_.borrado));
            }
            if (criteria.getClienteId() != null) {
                specification = specification.and(buildSpecification(criteria.getClienteId(),
                    root -> root.join(CLugarParada_.cliente, JoinType.LEFT).get(CCliente_.id)));
            }
        }
        return specification;
    }
}
