package com.whty.${projectModel}.service;

import com.whty.${projectModel}.common.Constants;
import com.whty.${projectModel}.domain.${className};
import com.whty.${projectModel}.model.${className}DTO;
import com.whty.${projectModel}.repository.${className}Repository;
import com.whty.${projectModel}.service.mapper.${className}Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ${className}}.
 */
@Service
@Transactional
public class ${className}Service {

    private final Logger log = LoggerFactory.getLogger(${className}Service.class);

    private final ${className}Repository ${littleClassName}Repository;

    private final ${className}Mapper ${littleClassName}Mapper;



    public ${className}Service(${className}Repository ${littleClassName}Repository, ${className}Mapper ${littleClassName}Mapper) {
        this.${littleClassName}Repository = ${littleClassName}Repository;
        this.${littleClassName}Mapper = ${littleClassName}Mapper;
    }

    /**
     * Save a ${littleClassName}.
     *
     * @param ${littleClassName}DTO the entity to save.
     * @return the persisted entity.
     */
    public ${className}DTO save(${className}DTO ${littleClassName}DTO) {
        log.debug("Request to save ${className} : {}", ${littleClassName}DTO);
        ${className} ${littleClassName} = ${littleClassName}Mapper.toEntity(${littleClassName}DTO);
        ${littleClassName} = ${littleClassName}Repository.save(${littleClassName});
        return ${littleClassName}Mapper.toDto(${littleClassName});
    }

    /**
     * Get all the ${littleClassName}s.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<${className}DTO> findAll(Pageable pageable) {
        log.debug("Request to get all ${className}s");
        return ${littleClassName}Repository.findAll(pageable)
            .map(${littleClassName}Mapper::toDto);
    }


    /**
     * Get one ${littleClassName} by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<${className}DTO> findOne(Long id) {
        log.debug("Request to get ${className} : {}", id);
        return ${littleClassName}Repository.findById(id)
            .map(${littleClassName}Mapper::toDto);
    }

    /**
     * Delete the ${littleClassName} by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ${className} : {}", id);
        ${littleClassName}Repository.deleteById(id);
    }




}
