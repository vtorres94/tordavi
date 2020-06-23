package mx.com.tordavi.service;

import mx.com.tordavi.domain.CAutobus;
import mx.com.tordavi.repository.CAutobusRepository;
import mx.com.tordavi.service.dto.CAutobusDTO;
import mx.com.tordavi.service.mapper.CAutobusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CAutobus}.
 */
@Service
@Transactional
public class CAutobusService {

    private final Logger log = LoggerFactory.getLogger(CAutobusService.class);

    private final CAutobusRepository cAutobusRepository;

    private final CAutobusMapper cAutobusMapper;

    public CAutobusService(CAutobusRepository cAutobusRepository, CAutobusMapper cAutobusMapper) {
        this.cAutobusRepository = cAutobusRepository;
        this.cAutobusMapper = cAutobusMapper;
    }

    /**
     * Save a cAutobus.
     *
     * @param cAutobusDTO the entity to save.
     * @return the persisted entity.
     */
    public CAutobusDTO save(CAutobusDTO cAutobusDTO) {
        log.debug("Request to save CAutobus : {}", cAutobusDTO);
        CAutobus cAutobus = cAutobusMapper.toEntity(cAutobusDTO);
        cAutobus = cAutobusRepository.save(cAutobus);
        return cAutobusMapper.toDto(cAutobus);
    }

    /**
     * Get all the cAutobuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CAutobusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CAutobuses");
        return cAutobusRepository.findAll(pageable)
            .map(cAutobusMapper::toDto);
    }

    /**
     * Get one cAutobus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CAutobusDTO> findOne(Long id) {
        log.debug("Request to get CAutobus : {}", id);
        return cAutobusRepository.findById(id)
            .map(cAutobusMapper::toDto);
    }

    /**
     * Delete the cAutobus by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CAutobus : {}", id);
        cAutobusRepository.deleteById(id);
    }
}
