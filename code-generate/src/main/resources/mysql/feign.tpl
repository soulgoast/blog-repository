package com.whty.${projectModel}.platform.client;

import com.whty.${projectModel}.common.Pagination;
import com.whty.${projectModel}.platform.client.bean.${className}Criteria;
import com.whty.${projectModel}.platform.client.bean.${className}DTO;
import com.whty.${projectModel}.platform.client.bean.wrapper.${className}Wrapper;
import java.util.Arrays;
import java.util.List;


@FeignClient(name = "demo-provider", path = "/api/${littleClassName}", qualifier = "${littleClassName}FeignClient")
public interface ${className}FeignClient {


    @PostMapping(value = "/save")
    ${className}DTO save(@RequestBody ${className}DTO ${littleClassName}DTO);

    @PostMapping(value = "/findOne/{id}")
     ${className}DTO findOne(@PathVariable("id") Long id);


    @PostMapping(value = "/delete/{id}")
    void delete(@PathVariable("id") Long id);

    @PostMapping(value = "/findByCriteria")
    List<${className}DTO> findByCriteria(@RequestBody ${className}Criteria criteria);

    @PostMapping(value = "/findByCriteriaAndPage")
    Pagination<${className}DTO> findByCriteria(@RequestBody ${className}Wrapper ${littleClassName}Wrapper);



    @PostMapping(value = "/findOne")
    ${className}DTO findOneByCriteria(@RequestBody ${className}Criteria criteria);
}


