package mx.com.tordavi.service;

import mx.com.tordavi.domain.CDireccion;
import mx.com.tordavi.repository.CDireccionRepository;
import mx.com.tordavi.service.dto.CDireccionDTO;
import mx.com.tordavi.service.mapper.CDireccionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CDireccion}.
 */
@Service
@Transactional
public class CDireccionService {

    private final Logger log = LoggerFactory.getLogger(CDireccionService.class);

    private final CDireccionRepository cDireccionRepository;

    private final CDireccionMapper cDireccionMapper;

    public CDireccionService(CDireccionRepository cDireccionRepository, CDireccionMapper cDireccionMapper) {
        this.cDireccionRepository = cDireccionRepository;
        this.cDireccionMapper = cDireccionMapper;
    }

    /**
     * Save a cDireccion.
     *
     * @param cDireccionDTO the entity to save.
     * @return the persisted entity.
     */
    public CDireccionDTO save(CDireccionDTO cDireccionDTO) {
        log.debug("Request to save CDireccion : {}", cDireccionDTO);
        CDireccion cDireccion = cDireccionMapper.toEntity(cDireccionDTO);
        cDireccion = cDireccionRepository.save(cDireccion);
        return cDireccionMapper.toDto(cDireccion);
    }

    /**
     * Get all the cDireccions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CDireccionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CDireccions");
        return cDireccionRepository.findAll(pageable)
            .map(cDireccionMapper::toDto);
    }

    /**
     * Get one cDireccion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CDireccionDTO> findOne(Long id) {
        log.debug("Request to get CDireccion : {}", id);
        return cDireccionRepository.findById(id)
            .map(cDireccionMapper::toDto);
    }

    /**
     * Delete the cDireccion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CDireccion : {}", id);
        cDireccionRepository.deleteById(id);
    }
}
