package mx.com.tordavi.service;

import mx.com.tordavi.domain.CLugarParada;
import mx.com.tordavi.repository.CLugarParadaRepository;
import mx.com.tordavi.service.dto.CLugarParadaDTO;
import mx.com.tordavi.service.mapper.CLugarParadaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CLugarParada}.
 */
@Service
@Transactional
public class CLugarParadaService {

    private final Logger log = LoggerFactory.getLogger(CLugarParadaService.class);

    private final CLugarParadaRepository cLugarParadaRepository;

    private final CLugarParadaMapper cLugarParadaMapper;

    public CLugarParadaService(CLugarParadaRepository cLugarParadaRepository, CLugarParadaMapper cLugarParadaMapper) {
        this.cLugarParadaRepository = cLugarParadaRepository;
        this.cLugarParadaMapper = cLugarParadaMapper;
    }

    /**
     * Save a cLugarParada.
     *
     * @param cLugarParadaDTO the entity to save.
     * @return the persisted entity.
     */
    public CLugarParadaDTO save(CLugarParadaDTO cLugarParadaDTO) {
        log.debug("Request to save CLugarParada : {}", cLugarParadaDTO);
        CLugarParada cLugarParada = cLugarParadaMapper.toEntity(cLugarParadaDTO);
        cLugarParada = cLugarParadaRepository.save(cLugarParada);
        return cLugarParadaMapper.toDto(cLugarParada);
    }

    /**
     * Get all the cLugarParadas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CLugarParadaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CLugarParadas");
        return cLugarParadaRepository.findAll(pageable)
            .map(cLugarParadaMapper::toDto);
    }

    /**
     * Get one cLugarParada by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CLugarParadaDTO> findOne(Long id) {
        log.debug("Request to get CLugarParada : {}", id);
        return cLugarParadaRepository.findById(id)
            .map(cLugarParadaMapper::toDto);
    }

    /**
     * Delete the cLugarParada by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CLugarParada : {}", id);
        cLugarParadaRepository.deleteById(id);
    }
}
