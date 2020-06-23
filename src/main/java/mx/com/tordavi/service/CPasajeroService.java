package mx.com.tordavi.service;

import mx.com.tordavi.domain.CPasajero;
import mx.com.tordavi.repository.CPasajeroRepository;
import mx.com.tordavi.service.dto.CPasajeroDTO;
import mx.com.tordavi.service.mapper.CPasajeroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Service Implementation for managing {@link CPasajero}.
 */
@Service
@Transactional
public class CPasajeroService {

    private final Logger log = LoggerFactory.getLogger(CPasajeroService.class);

    private final CPasajeroRepository pasajeroRepository;

    private final CPasajeroMapper pasajeroMapper;

    @PersistenceContext
    private EntityManager em;

/*
    @PersistenceContext    
    private EntityManager em;
*/
    public CPasajeroService(CPasajeroRepository pasajeroRepository, CPasajeroMapper pasajeroMapper) {
        this.pasajeroRepository = pasajeroRepository;
        this.pasajeroMapper = pasajeroMapper;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    /**
     * Save a pasajero.
     *
     * @param pasajeroDTO the entity to save.
     * @return the persisted entity.
     */
    public CPasajeroDTO save(CPasajeroDTO pasajeroDTO) {
        log.debug("Request to save CPasajero : {}", pasajeroDTO);

        CPasajero pasajero = pasajeroMapper.toEntity(pasajeroDTO);
        log.debug("Valor que lleva fecha fin seguna parte: {}", pasajeroRepository.save(pasajero));
        pasajero = pasajeroRepository.save(pasajero);
        return pasajeroMapper.toDto(pasajero);
    }

    public CPasajeroDTO borrar(CPasajeroDTO pasajeroDTO) {
        return null;
    }

    /**
     * Get all the pasajeros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
    */
    @Transactional(readOnly = true)
    public Page<CPasajeroDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CPasajero");
        return pasajeroRepository.findAll(pageable)
            .map(pasajeroMapper::toDto);
    }
    
    
    /**
     * Get one pasajero by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CPasajeroDTO> findOne(Long id) {
        log.debug("Request to get CPasajero : {}", id);
        return pasajeroRepository.findById(id)
            .map(pasajeroMapper::toDto);
    }

    /**
     * Delete the pasajero by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CPasajero : {}", id);
        pasajeroRepository.deleteById(id);
    }

    /**
     *
     * @param pasajeroDTO the entity to save.
     * @return the persisted entity.
     */
    /* 
    public int duplicateCreate(CPasajeroDTO pasajeroDTO) {
        log.debug("Request to duplicate CPasajero : {}", pasajeroDTO);
        CPasajero pasajero = pasajeroMapper.toEntity(pasajeroDTO);
        List<CPasajero> lista = pasajeroRepository.duplicateCreate(0, pasajero.getClave(), pasajero.getCliente(), pasajero.getPaciente(), pasajero.getTipoCPasajero());
        return lista.size() ;
    } */

    /**
     *
     * @param pasajeroDTO the entity to save.
     * @return the persisted entity.
     */
    /* 
    public int duplicateUpdate(CPasajeroDTO pasajeroDTO) {
        log.debug("Request to duplicate CPasajero : {}", pasajeroDTO);
        CPasajero pasajero = pasajeroMapper.toEntity(pasajeroDTO);
        List<CPasajero> lista = pasajeroRepository.duplicateUpdate(0, pasajero.getId(), pasajero.getCurp(), pasajero.getCliente(), pasajero.getPaciente(), pasajero.getTipoCPasajero());
        return lista.size() ;
    } */
    
}