package mx.com.tordavi.service;

import mx.com.tordavi.domain.CReservacion;
import mx.com.tordavi.repository.CReservacionRepository;
import mx.com.tordavi.service.dto.CReservacionDTO;
import mx.com.tordavi.service.mapper.CReservacionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CReservacion}.
 */
@Service
@Transactional
public class CReservacionService {

    private final Logger log = LoggerFactory.getLogger(CReservacionService.class);

    private final CReservacionRepository cReservacionRepository;

    private final CReservacionMapper cReservacionMapper;

    public CReservacionService(CReservacionRepository cReservacionRepository, CReservacionMapper cReservacionMapper) {
        this.cReservacionRepository = cReservacionRepository;
        this.cReservacionMapper = cReservacionMapper;
    }

    /**
     * Save a cReservacion.
     *
     * @param cReservacionDTO the entity to save.
     * @return the persisted entity.
     */
    public CReservacionDTO save(CReservacionDTO cReservacionDTO) {
        log.debug("Request to save CReservacion : {}", cReservacionDTO);
        CReservacion cReservacion = cReservacionMapper.toEntity(cReservacionDTO);
        cReservacion = cReservacionRepository.save(cReservacion);
        return cReservacionMapper.toDto(cReservacion);
    }

    /**
     * Get all the cReservacions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CReservacionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CReservacions");
        return cReservacionRepository.findAll(pageable)
            .map(cReservacionMapper::toDto);
    }

    /**
     * Get one cReservacion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CReservacionDTO> findOne(Long id) {
        log.debug("Request to get CReservacion : {}", id);
        return cReservacionRepository.findById(id)
            .map(cReservacionMapper::toDto);
    }

    /**
     * Delete the cReservacion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CReservacion : {}", id);
        cReservacionRepository.deleteById(id);
    }
}
