package com.qunce.code.generate.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@Configuration
@Data
@Accessors(chain = true)
public class GenerateConfig {

    @Value("${generate.generateAddress}")
    private String generateAddress;

    @Value("${generate.package}")
    private String packageName;



}
