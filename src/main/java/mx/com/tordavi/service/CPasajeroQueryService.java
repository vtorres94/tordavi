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

import mx.com.tordavi.domain.CPasajero;
import mx.com.tordavi.domain.*; // for static metamodels
import mx.com.tordavi.repository.CPasajeroRepository;
import mx.com.tordavi.service.dto.CPasajeroCriteria;
import mx.com.tordavi.service.dto.CPasajeroDTO;
import mx.com.tordavi.service.mapper.CPasajeroMapper;

/**
 * Service for executing complex queries for {@link CPasajero} entities in the database.
 * The main input is a {@link CPasajeroCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CPasajeroDTO} or a {@link Page} of {@link CPasajeroDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CPasajeroQueryService extends QueryService<CPasajero> {

    private final Logger log = LoggerFactory.getLogger(CPasajeroQueryService.class);

    private final CPasajeroRepository cPasajeroRepository;

    private final CPasajeroMapper cPasajeroMapper;

    public CPasajeroQueryService(CPasajeroRepository cPasajeroRepository, CPasajeroMapper cPasajeroMapper) {
        this.cPasajeroRepository = cPasajeroRepository;
        this.cPasajeroMapper = cPasajeroMapper;
    }

    /**
     * Return a {@link List} of {@link CPasajeroDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CPasajeroDTO> findByCriteria(CPasajeroCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CPasajero> specification = createSpecification(criteria);
        return cPasajeroMapper.toDto(cPasajeroRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CPasajeroDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CPasajeroDTO> findByCriteria(CPasajeroCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CPasajero> specification = createSpecification(criteria);
        return cPasajeroRepository.findAll(specification, page)
            .map(cPasajeroMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CPasajeroCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CPasajero> specification = createSpecification(criteria);
        return cPasajeroRepository.count(specification);
    }

    /**
     * Function to convert {@link CPasajeroCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CPasajero> createSpecification(CPasajeroCriteria criteria) {
        Specification<CPasajero> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CPasajero_.id));
            }
            if (criteria.getNombreCompleto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombreCompleto(), CPasajero_.nombreCompleto));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), CPasajero_.nombre));
            }
            if (criteria.getSegundoNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSegundoNombre(), CPasajero_.segundoNombre));
            }
            if (criteria.getApellido() != null) {
                specification = specification.and(buildStringSpecification(criteria.getApellido(), CPasajero_.apellido));
            }
            if (criteria.getSegundoApellido() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSegundoApellido(), CPasajero_.segundoApellido));
            }
            if (criteria.getEdad() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEdad(), CPasajero_.edad));
            }
            if (criteria.getCurp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCurp(), CPasajero_.curp));
            }
            if (criteria.getCiudadania() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCiudadania(), CPasajero_.ciudadania));
            }
            if (criteria.getIdUsuarioCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioCreacion(), CPasajero_.idUsuarioCreacion));
            }
            if (criteria.getFechaCreacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaCreacion(), CPasajero_.fechaCreacion));
            }
            if (criteria.getIdUsuarioActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuarioActualizacion(), CPasajero_.idUsuarioActualizacion));
            }
            if (criteria.getFechaActualizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaActualizacion(), CPasajero_.fechaActualizacion));
            }
            if (criteria.getNotas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotas(), CPasajero_.notas));
            }
            if (criteria.getEstatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstatus(), CPasajero_.estatus));
            }
            if (criteria.getBorrado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBorrado(), CPasajero_.borrado));
            }
            if (criteria.getClienteId() != null) {
                specification = specification.and(buildSpecification(criteria.getClienteId(),
                    root -> root.join(CPasajero_.cliente, JoinType.LEFT).get(CCliente_.id)));
            }
        }
        return specification;
    }
}
