package mx.com.tordavi.service;

import mx.com.tordavi.domain.CCorrida;
import mx.com.tordavi.repository.CCorridaRepository;
import mx.com.tordavi.service.dto.CCorridaDTO;
import mx.com.tordavi.service.mapper.CCorridaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CCorrida}.
 */
@Service
@Transactional
public class CCorridaService {

    private final Logger log = LoggerFactory.getLogger(CCorridaService.class);

    private final CCorridaRepository cCorridaRepository;

    private final CCorridaMapper cCorridaMapper;

    public CCorridaService(CCorridaRepository cCorridaRepository, CCorridaMapper cCorridaMapper) {
        this.cCorridaRepository = cCorridaRepository;
        this.cCorridaMapper = cCorridaMapper;
    }

    /**
     * Save a cCorrida.
     *
     * @param cCorridaDTO the entity to save.
     * @return the persisted entity.
     */
    public CCorridaDTO save(CCorridaDTO cCorridaDTO) {
        log.debug("Request to save CCorrida : {}", cCorridaDTO);
        CCorrida cCorrida = cCorridaMapper.toEntity(cCorridaDTO);
        cCorrida = cCorridaRepository.save(cCorrida);
        return cCorridaMapper.toDto(cCorrida);
    }

    /**
     * Get all the cCorridas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CCorridaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CCorridas");
        return cCorridaRepository.findAll(pageable)
            .map(cCorridaMapper::toDto);
    }

    /**
     * Get one cCorrida by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CCorridaDTO> findOne(Long id) {
        log.debug("Request to get CCorrida : {}", id);
        return cCorridaRepository.findById(id)
            .map(cCorridaMapper::toDto);
    }

    /**
     * Delete the cCorrida by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CCorrida : {}", id);
        cCorridaRepository.deleteById(id);
    }
}
