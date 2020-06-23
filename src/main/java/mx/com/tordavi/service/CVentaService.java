package mx.com.tordavi.service;

import mx.com.tordavi.domain.CVenta;
import mx.com.tordavi.repository.CVentaRepository;
import mx.com.tordavi.service.dto.CVentaDTO;
import mx.com.tordavi.service.mapper.CVentaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CVenta}.
 */
@Service
@Transactional
public class CVentaService {

    private final Logger log = LoggerFactory.getLogger(CVentaService.class);

    private final CVentaRepository cVentaRepository;

    private final CVentaMapper cVentaMapper;

    public CVentaService(CVentaRepository cVentaRepository, CVentaMapper cVentaMapper) {
        this.cVentaRepository = cVentaRepository;
        this.cVentaMapper = cVentaMapper;
    }

    /**
     * Save a cVenta.
     *
     * @param cVentaDTO the entity to save.
     * @return the persisted entity.
     */
    public CVentaDTO save(CVentaDTO cVentaDTO) {
        log.debug("Request to save CVenta : {}", cVentaDTO);
        CVenta cVenta = cVentaMapper.toEntity(cVentaDTO);
        cVenta = cVentaRepository.save(cVenta);
        return cVentaMapper.toDto(cVenta);
    }

    /**
     * Get all the cVentas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CVentaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CVentas");
        return cVentaRepository.findAll(pageable)
            .map(cVentaMapper::toDto);
    }

    /**
     * Get one cVenta by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CVentaDTO> findOne(Long id) {
        log.debug("Request to get CVenta : {}", id);
        return cVentaRepository.findById(id)
            .map(cVentaMapper::toDto);
    }

    /**
     * Delete the cVenta by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CVenta : {}", id);
        cVentaRepository.deleteById(id);
    }
}
