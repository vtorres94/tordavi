package mx.com.tordavi.service;

import mx.com.tordavi.domain.CDetalleReservacion;
import mx.com.tordavi.repository.CDetalleReservacionRepository;
import mx.com.tordavi.service.dto.CDetalleReservacionDTO;
import mx.com.tordavi.service.mapper.CDetalleReservacionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CDetalleReservacion}.
 */
@Service
@Transactional
public class CDetalleReservacionService {

    private final Logger log = LoggerFactory.getLogger(CDetalleReservacionService.class);

    private final CDetalleReservacionRepository cDetalleReservacionRepository;

    private final CDetalleReservacionMapper cDetalleReservacionMapper;

    public CDetalleReservacionService(CDetalleReservacionRepository cDetalleReservacionRepository, CDetalleReservacionMapper cDetalleReservacionMapper) {
        this.cDetalleReservacionRepository = cDetalleReservacionRepository;
        this.cDetalleReservacionMapper = cDetalleReservacionMapper;
    }

    /**
     * Save a cDetalleReservacion.
     *
     * @param cDetalleReservacionDTO the entity to save.
     * @return the persisted entity.
     */
    public CDetalleReservacionDTO save(CDetalleReservacionDTO cDetalleReservacionDTO) {
        log.debug("Request to save CDetalleReservacion : {}", cDetalleReservacionDTO);
        CDetalleReservacion cDetalleReservacion = cDetalleReservacionMapper.toEntity(cDetalleReservacionDTO);
        cDetalleReservacion = cDetalleReservacionRepository.save(cDetalleReservacion);
        return cDetalleReservacionMapper.toDto(cDetalleReservacion);
    }

    /**
     * Get all the cDetalleReservacions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CDetalleReservacionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CDetalleReservacions");
        return cDetalleReservacionRepository.findAll(pageable)
            .map(cDetalleReservacionMapper::toDto);
    }

    /**
     * Get one cDetalleReservacion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CDetalleReservacionDTO> findOne(Long id) {
        log.debug("Request to get CDetalleReservacion : {}", id);
        return cDetalleReservacionRepository.findById(id)
            .map(cDetalleReservacionMapper::toDto);
    }

    /**
     * Delete the cDetalleReservacion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CDetalleReservacion : {}", id);
        cDetalleReservacionRepository.deleteById(id);
    }
}
