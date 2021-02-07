package com.whty.dcs.provider;

import com.whty.dcs.common.Pagination;
import com.whty.dcs.model.${className}Criteria;
import com.whty.dcs.model.${className}DTO;
import com.whty.dcs.model.wrapper.${className}Wrapper;
import com.whty.dcs.service.${className}QueryService;
import com.whty.dcs.service.${className}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/${littleClassName}")
@Slf4j
public class ${className}Provider {

    @Autowired
    private ${className}Service ${littleClassName}Service;
    @Autowired
    private ${className}QueryService ${littleClassName}QueryService;

    @PostMapping(value = "/save")
    public ${className}DTO save(@RequestBody ${className}DTO ${littleClassName}DTO) {
        return ${littleClassName}Service.save(${littleClassName}DTO);
    }

    @PostMapping(value = "/findOne/{id}")
    public ${className}DTO findOne(@PathVariable("id") Long id) {
        return ${littleClassName}Service.findOne(id).orElse(null);
    }


    @PostMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        ${littleClassName}Service.delete(id);
    }

    @PostMapping(value = "/findByCriteria")
    public List<${className}DTO> findByCriteria(@RequestBody ${className}Criteria criteria) {
        return ${littleClassName}QueryService.findByCriteria(criteria);
    }

    @PostMapping(value = "/findByCriteriaAndPage")
    public Pagination<${className}DTO> findByCriteria(@RequestBody ${className}Wrapper ${littleClassName}Wrapper) {
        return ${littleClassName}QueryService.findByCriteria(${littleClassName}Wrapper.getCriteria(), Pagination.pageable(${littleClassName}Wrapper.getPage()));
    }



    @PostMapping(value = "/findOne")
    public ${className}DTO findOneByCriteria(@RequestBody ${className}Criteria criteria) {
        return ${littleClassName}QueryService.findOneByCriteria(criteria);
    }
}


