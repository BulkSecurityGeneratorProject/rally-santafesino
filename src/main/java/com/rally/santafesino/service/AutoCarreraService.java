package com.rally.santafesino.service;

import com.rally.santafesino.domain.AutoCarrera;
import com.rally.santafesino.repository.AutoCarreraRepository;
import com.rally.santafesino.service.dto.AutoCarreraDTO;
import com.rally.santafesino.service.mapper.AutoCarreraMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing AutoCarrera.
 */
@Service
@Transactional
public class AutoCarreraService {

    private final Logger log = LoggerFactory.getLogger(AutoCarreraService.class);

    private final AutoCarreraRepository autoCarreraRepository;

    private final AutoCarreraMapper autoCarreraMapper;

    public AutoCarreraService(AutoCarreraRepository autoCarreraRepository, AutoCarreraMapper autoCarreraMapper) {
        this.autoCarreraRepository = autoCarreraRepository;
        this.autoCarreraMapper = autoCarreraMapper;
    }

    /**
     * Save a autoCarrera.
     *
     * @param autoCarreraDTO the entity to save
     * @return the persisted entity
     */
    public AutoCarreraDTO save(AutoCarreraDTO autoCarreraDTO) {
        log.debug("Request to save AutoCarrera : {}", autoCarreraDTO);
        AutoCarrera autoCarrera = autoCarreraMapper.toEntity(autoCarreraDTO);
        autoCarrera = autoCarreraRepository.save(autoCarrera);
        return autoCarreraMapper.toDto(autoCarrera);
    }

    /**
     * Get all the autoCarreras.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AutoCarreraDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AutoCarreras");
        return autoCarreraRepository.findAll(pageable)
            .map(autoCarreraMapper::toDto);
    }

    /**
     * Get one autoCarrera by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public AutoCarreraDTO findOne(Long id) {
        log.debug("Request to get AutoCarrera : {}", id);
        AutoCarrera autoCarrera = autoCarreraRepository.findOne(id);
        return autoCarreraMapper.toDto(autoCarrera);
    }

    /**
     * Delete the autoCarrera by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AutoCarrera : {}", id);
        autoCarreraRepository.delete(id);
    }
}