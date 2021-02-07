package com.whty.${projectModel}.service;

import java.util.List;

import com.whty.${projectModel}.common.Pagination;
import com.whty.${projectModel}.domain.${className};
import com.whty.${projectModel}.domain.${className}_;
import com.whty.${projectModel}.service.dto.${className}Criteria;
import com.whty.${projectModel}.service.dto.${className}DTO;
import com.whty.${projectModel}.repository.${className}Repository;
import com.whty.${projectModel}.service.mapper.${className}Mapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ${className}QueryService extends QueryService<${className}> {

    private final Logger log = LoggerFactory.getLogger(${className}QueryService.class);

    private final ${className}Repository ${littleClassName}Repository;

    private final ${className}Mapper ${littleClassName}Mapper;

    public ${className}QueryService(${className}Repository ${littleClassName}Repository, ${className}Mapper ${littleClassName}Mapper) {
        this.${littleClassName}Repository = ${littleClassName}Repository;
        this.${littleClassName}Mapper = ${littleClassName}Mapper;
    }

    public ${className}DTO findOneByCriteria(${className}Criteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<${className}> specification = createSpecification(criteria);
        return ${littleClassName}Repository.findOne(specification).map(${littleClassName}Mapper::toDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<${className}DTO> findByCriteria(${className}Criteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<${className}> specification = createSpecification(criteria);
        return ${littleClassName}Mapper.toDto(${littleClassName}Repository.findAll(specification));
    }

    @Transactional(readOnly = true)
    public Pagination<${className}DTO> findByCriteria(${className}Criteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<${className}> specification = createSpecification(criteria);
        Page<${className}> result = ${littleClassName}Repository.findAll(specification, page);
        return ${littleClassName}Mapper.toDto(result);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(${className}Criteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<${className}> specification = createSpecification(criteria);
        return ${littleClassName}Repository.count(specification);
    }

    protected Specification<${className}> createSpecification(${className}Criteria criteria) {
        Specification<${className}> specification = Specification.where(null);
        if (criteria != null) {
         <#list fields as field>
          if (criteria.get${field.upperName}() != null) {
                         specification = specification.and(${field.buildSpecification}(criteria.get${field.upperName}(), ${className}_.${field.name}));
                     }
           </#list>
        }
        return specification;
    }
}
