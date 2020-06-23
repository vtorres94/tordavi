package mx.com.tordavi.service;

import mx.com.tordavi.domain.CContacto;
import mx.com.tordavi.repository.CContactoRepository;
import mx.com.tordavi.service.dto.CContactoDTO;
import mx.com.tordavi.service.mapper.CContactoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CContacto}.
 */
@Service
@Transactional
public class CContactoService {

    private final Logger log = LoggerFactory.getLogger(CContactoService.class);

    private final CContactoRepository cContactoRepository;

    private final CContactoMapper cContactoMapper;

    public CContactoService(CContactoRepository cContactoRepository, CContactoMapper cContactoMapper) {
        this.cContactoRepository = cContactoRepository;
        this.cContactoMapper = cContactoMapper;
    }

    /**
     * Save a cContacto.
     *
     * @param cContactoDTO the entity to save.
     * @return the persisted entity.
     */
    public CContactoDTO save(CContactoDTO cContactoDTO) {
        log.debug("Request to save CContacto : {}", cContactoDTO);
        CContacto cContacto = cContactoMapper.toEntity(cContactoDTO);
        cContacto = cContactoRepository.save(cContacto);
        return cContactoMapper.toDto(cContacto);
    }

    /**
     * Get all the cContactos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CContactoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CContactos");
        return cContactoRepository.findAll(pageable)
            .map(cContactoMapper::toDto);
    }

    /**
     * Get one cContacto by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CContactoDTO> findOne(Long id) {
        log.debug("Request to get CContacto : {}", id);
        return cContactoRepository.findById(id)
            .map(cContactoMapper::toDto);
    }

    /**
     * Delete the cContacto by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CContacto : {}", id);
        cContactoRepository.deleteById(id);
    }
}
