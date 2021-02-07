package com.qunce.code.generate.config;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Version;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@Slf4j
@Configuration
public class ProjectConfig {

    @Bean
    public freemarker.template.Configuration initConfig() {
        try {
            String rootPath = ProjectConfig.class.getResource("/").getPath();
            log.info("rootPath:{}", rootPath);
            StringTemplateLoader templateLoader = new StringTemplateLoader();
            templateLoader.putTemplate("dto", IOUtils.toString(new FileInputStream(new File(rootPath, "dto.tpl")), "UTF-8"));
            templateLoader.putTemplate("criteria", IOUtils.toString(new FileInputStream(new File(rootPath, "criteria.tpl")), "UTF-8"));
            templateLoader.putTemplate("wrapper", IOUtils.toString(new FileInputStream(new File(rootPath, "wrapper.tpl")), "UTF-8"));
            templateLoader.putTemplate("mapper", IOUtils.toString(new FileInputStream(new File(rootPath, "mapper.tpl")), "UTF-8"));
            templateLoader.putTemplate("mapper", IOUtils.toString(new FileInputStream(new File(rootPath, "mapper.tpl")), "UTF-8"));
            templateLoader.putTemplate("repository", IOUtils.toString(new FileInputStream(new File(rootPath, "repository.tpl")), "UTF-8"));

            templateLoader.putTemplate("service", IOUtils.toString(new FileInputStream(new File(rootPath, "service.tpl")), "UTF-8"));
            templateLoader.putTemplate("queryService", IOUtils.toString(new FileInputStream(new File(rootPath, "queryService.tpl")), "UTF-8"));
            templateLoader.putTemplate("provider", IOUtils.toString(new FileInputStream(new File(rootPath, "provider.tpl")), "UTF-8"));
            templateLoader.putTemplate("feign", IOUtils.toString(new FileInputStream(new File(rootPath, "feign.tpl")), "UTF-8"));
            freemarker.template.Configuration cfg = new freemarker.template.Configuration(new Version(2, 3, 23));
            cfg.setTemplateLoader(templateLoader);
            return cfg;
        } catch (Exception e) {
            log.info("加载配置文件异常");
            throw new IllegalArgumentException("加载配置文件异常");
        }
    }
}
