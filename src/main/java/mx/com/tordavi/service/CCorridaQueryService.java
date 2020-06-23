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

import mx.com.tordavi.domain.CCorrida;
import mx.com.tordavi.domain.*; // for static metamodels
import mx.com.tordavi.repository.CCorridaRepository;
import mx.com.tordavi.service.dto.CCorridaCriteria;
import mx.com.tordavi.service.dto.CCorridaDTO;
import mx.com.tordavi.service.mapper.CCorridaMapper;

/**
 * Service for executing complex queries for {@link CCorrida} entities in the database.
 * The main input is a {@link CCorridaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CCorridaDTO} or a {@link Page} of {@link CCorridaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CCorridaQueryService extends QueryService<CCorrida> {

    private final Logger log = LoggerFactory.getLogger(CCorridaQueryService.class);

    private final CCorridaRepository cCorridaRepository;

    private final CCorridaMapper cCorridaMapper;

    public CCorridaQueryService(CCorridaRepository cCorridaRepository, CCorridaMapper cCorridaMapper) {
        this.cCorridaRepository = cCorridaRepository;
        this.cCorridaMapper = cCorridaMapper;
    }

    /**
     * Return a {@link List} of {@link CCorridaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CCorridaDTO> findByCriteria(CCorridaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CCorrida> specification = createSpecification(criteria);
        return cCorridaMapper.toDto(cCorridaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CCorridaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CCorridaDTO> findByCriteria(CCorridaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CCorrida> specification = createSpecification(criteria);
        return cCorridaRepository.findAll(specification, page)
            .map(cCorridaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CCorridaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CCorrida> specification = createSpecification(criteria);
        return cCorridaRepository.count(specification);
    }

    /**
     * Function to convert {@link CCorridaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CCorrida> createSpecification(CCorridaCriteria criteria) {
        Specification<CCorrida> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CCorrida_.id));
            }
            if (criteria.getClaveCorrida() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClaveCorrida(), CCorrida_.claveCorrida));
            }
            if (criteria.getHoraSalida() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHoraSalida(), CCorrida_.horaSalida));
            }
            if (criteria.getHoraLllegada() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHoraLllegada(), CCorrida_.horaLllegada));
            }
            if (criteria.getConexion() != null) {
                specification = specification.and(buildSpecification(criteria.getConexion(), CCorrida_.conexion));
            }
            if (criteria.getLugarConexion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLugarConexion(), CCorrida_.lugarConexion));
            }
            if (criteria.getDiasSalida() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDiasSalida(), CCorrida_.diasSalida));
            }
            if (criteria.getComentarios() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComentarios(), CCorrida_.comentarios));
            }
            if (criteria.getIdUsuarioCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioCreacion(), CCorrida_.idUsuarioCreacion));
            }
            if (criteria.getFechaCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaCreacion(), CCorrida_.fechaCreacion));
            }
            if (criteria.getIdUsuarioActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioActualizacion(), CCorrida_.idUsuarioActualizacion));
            }
            if (criteria.getFechaActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaActualizacion(), CCorrida_.fechaActualizacion));
            }
            if (criteria.getNotas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotas(), CCorrida_.notas));
            }
            if (criteria.getEstatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstatus(), CCorrida_.estatus));
            }
            if (criteria.getBorrado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBorrado(), CCorrida_.borrado));
            }
            if (criteria.getClienteId() != null) {
                specification = specification.and(buildSpecification(criteria.getClienteId(),
                    root -> root.join(CCorrida_.cliente, JoinType.LEFT).get(CCliente_.id)));
            }
            if (criteria.getAutobusId() != null) {
                specification = specification.and(buildSpecification(criteria.getAutobusId(),
                    root -> root.join(CCorrida_.autobus, JoinType.LEFT).get(CAutobus_.id)));
            }
            if (criteria.getLugarSalidaId() != null) {
                specification = specification.and(buildSpecification(criteria.getLugarSalidaId(),
                    root -> root.join(CCorrida_.lugarSalida, JoinType.LEFT).get(CLugarParada_.id)));
            }
            if (criteria.getLugarLlegadaId() != null) {
                specification = specification.and(buildSpecification(criteria.getLugarLlegadaId(),
                    root -> root.join(CCorrida_.lugarLlegada, JoinType.LEFT).get(CLugarParada_.id)));
            }
        }
        return specification;
    }
}
