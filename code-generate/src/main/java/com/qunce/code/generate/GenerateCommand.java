package com.qunce.code.generate;

import com.qunce.code.generate.service.CodeService;
import com.qunce.code.generate.service.dto.TableDTO;
import freemarker.cache.StringTemplateLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GenerateCommand implements CommandLineRunner {

    private final CodeService codeService;

    @Override
    public void run(String... args) throws Exception {
        List<TableDTO> tablesList = codeService.findTablesList();
        if (CollectionUtils.isEmpty(tablesList)) {
            return;
        }
        tablesList.stream().map(TableDTO::getTabNam).forEach(log::debug);
        codeService.generateProcess(tablesList);
    }


}
