package mx.com.tordavi.service;

import mx.com.tordavi.domain.CDocumentoViaje;
import mx.com.tordavi.repository.CDocumentoViajeRepository;
import mx.com.tordavi.service.dto.CDocumentoViajeDTO;
import mx.com.tordavi.service.mapper.CDocumentoViajeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CDocumentoViaje}.
 */
@Service
@Transactional
public class CDocumentoViajeService {

    private final Logger log = LoggerFactory.getLogger(CDocumentoViajeService.class);

    private final CDocumentoViajeRepository cDocumentoViajeRepository;

    private final CDocumentoViajeMapper cDocumentoViajeMapper;

    public CDocumentoViajeService(CDocumentoViajeRepository cDocumentoViajeRepository, CDocumentoViajeMapper cDocumentoViajeMapper) {
        this.cDocumentoViajeRepository = cDocumentoViajeRepository;
        this.cDocumentoViajeMapper = cDocumentoViajeMapper;
    }

    /**
     * Save a cDocumentoViaje.
     *
     * @param cDocumentoViajeDTO the entity to save.
     * @return the persisted entity.
     */
    public CDocumentoViajeDTO save(CDocumentoViajeDTO cDocumentoViajeDTO) {
        log.debug("Request to save CDocumentoViaje : {}", cDocumentoViajeDTO);
        CDocumentoViaje cDocumentoViaje = cDocumentoViajeMapper.toEntity(cDocumentoViajeDTO);
        cDocumentoViaje = cDocumentoViajeRepository.save(cDocumentoViaje);
        return cDocumentoViajeMapper.toDto(cDocumentoViaje);
    }

    /**
     * Get all the cDocumentoViajes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CDocumentoViajeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CDocumentoViajes");
        return cDocumentoViajeRepository.findAll(pageable)
            .map(cDocumentoViajeMapper::toDto);
    }

    /**
     * Get one cDocumentoViaje by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CDocumentoViajeDTO> findOne(Long id) {
        log.debug("Request to get CDocumentoViaje : {}", id);
        return cDocumentoViajeRepository.findById(id)
            .map(cDocumentoViajeMapper::toDto);
    }

    /**
     * Delete the cDocumentoViaje by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CDocumentoViaje : {}", id);
        cDocumentoViajeRepository.deleteById(id);
    }
}
