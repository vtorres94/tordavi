package mx.com.tordavi.service;

import mx.com.tordavi.domain.CPendiente;
import mx.com.tordavi.repository.CPendienteRepository;
import mx.com.tordavi.service.dto.CPendienteDTO;
import mx.com.tordavi.service.mapper.CPendienteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CPendiente}.
 */
@Service
@Transactional
public class CPendienteService {

    private final Logger log = LoggerFactory.getLogger(CPendienteService.class);

    private final CPendienteRepository cPendienteRepository;

    private final CPendienteMapper cPendienteMapper;

    public CPendienteService(CPendienteRepository cPendienteRepository, CPendienteMapper cPendienteMapper) {
        this.cPendienteRepository = cPendienteRepository;
        this.cPendienteMapper = cPendienteMapper;
    }

    /**
     * Save a cPendiente.
     *
     * @param cPendienteDTO the entity to save.
     * @return the persisted entity.
     */
    public CPendienteDTO save(CPendienteDTO cPendienteDTO) {
        log.debug("Request to save CPendiente : {}", cPendienteDTO);
        CPendiente cPendiente = cPendienteMapper.toEntity(cPendienteDTO);
        cPendiente = cPendienteRepository.save(cPendiente);
        return cPendienteMapper.toDto(cPendiente);
    }

    /**
     * Get all the cPendientes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CPendienteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CPendientes");
        return cPendienteRepository.findAll(pageable)
            .map(cPendienteMapper::toDto);
    }

    /**
     * Get one cPendiente by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CPendienteDTO> findOne(Long id) {
        log.debug("Request to get CPendiente : {}", id);
        return cPendienteRepository.findById(id)
            .map(cPendienteMapper::toDto);
    }

    /**
     * Delete the cPendiente by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CPendiente : {}", id);
        cPendienteRepository.deleteById(id);
    }
}
