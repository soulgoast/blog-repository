package com.qunce.code.generate.service;

import com.qunce.code.generate.config.GenerateConfig;
import com.qunce.code.generate.dao.CodeMakerDao;
import com.qunce.code.generate.service.dto.ColumnDTO;
import com.qunce.code.generate.service.dto.FieldDto;
import com.qunce.code.generate.service.dto.ProjectInfoDTO;
import com.qunce.code.generate.service.dto.TableDTO;
import com.qunce.code.generate.utils.ColumnUtils;
import com.qunce.code.generate.utils.DatabaseDesignUtils;
import com.qunce.code.generate.utils.ObjStrUtls;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CodeService {

    private final CodeMakerDao codeMakerDao;

    private final Configuration configuration;

    private final GenerateConfig generateConfig;

    public List<TableDTO> findTablesList() {
        return  codeMakerDao.queryTableName();
    }

    public void generateProcess(List<TableDTO> tablesList) {

        Map<String, Object> dataMap = new HashMap<>();

        List<Map<String, Object>> newsList=new ArrayList<>();

        dataMap.put("tableList",newsList);

        tablesList.stream().forEach(table -> {
            ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
            projectInfoDTO.setTableName(table.getTabNam());
            projectInfoDTO.setTableNameCn(table.getComment());
            String objNam = ObjStrUtls.getObjNam(table.getTabNam());
            String objCNam = ObjStrUtls.getObjCNam(objNam);
            projectInfoDTO.setObjectName(objCNam);
            List<FieldDto> fieldDtos = this.generateProcess(projectInfoDTO);

            Map<String, Object> map=new HashMap<>();
            map.put("tableNameCn", table.getComment());
            map.put("tableNameEn", table.getTabNam());
            List<Map<String, String>> columnList = new ArrayList<>();
            for (FieldDto fieldDto : fieldDtos) {
                String name = fieldDto.getName();
                String fieldComment = fieldDto.getFieldComment();
                String type = fieldDto.getType();
                String fieldConstraint = fieldDto.getFieldConstraint();
                Map<String, String> stringMap = new HashMap<>();
                stringMap.put("columnNameEn", name);
                stringMap.put("columnNamnCn", fieldComment);
                stringMap.put("type", type);
                stringMap.put("isNull", fieldConstraint);
                stringMap.put("comment", fieldComment);
                columnList.add(stringMap);
            }

            map.put("columnList", columnList);
            newsList.add(map);
        });

        //文件唯一名称
        String fileOnlyName = "用freemarker导出的Word文档_" + ".doc";

        /** 生成word */
        DatabaseDesignUtils.createWord(dataMap, "table.ftl", generateConfig.getGenerateAddress(), fileOnlyName);
    }

    public List<FieldDto> generateProcess(ProjectInfoDTO projectInfo) {
        List<FieldDto> fieldDtos = retrieveFieldsByTablename(projectInfo.getTableName());
        fieldDtos.stream().map(FieldDto::toString).forEach(log::debug);
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", projectInfo.getTableName());
        map.put("fields", fieldDtos);
        map.put("projectModel", generateConfig.getPackageName());
        map.put("DtoDesc", "DtoDesc");
        map.put("className", projectInfo.getObjectName());
        map.put("serUid","1L");

        generateEntity("domain", projectInfo.getObjectName(), map);
        return fieldDtos;

    }

    public void generateEntity(String templateFile, String objectName, Map<String, Object> map) {
        try {
            String codeFilePath = getEntityCodeFolder(generateConfig.getPackageName() + ".domain." + objectName);
            String codeFileFolder = StringUtils.substringBeforeLast(codeFilePath, File.separator);
            FileUtils.forceMkdir(new File((new StringBuilder(String.valueOf(codeFileFolder))).append(File.separator).toString()));
            Template template = configuration.getTemplate(templateFile);
            OutputStreamWriter outputstreamwriter = new OutputStreamWriter(new FileOutputStream(codeFilePath), "utf-8");
            template.process(map, outputstreamwriter);
            outputstreamwriter.close();
        } catch (TemplateException templateexception) {
            templateexception.printStackTrace();
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }

    public String getEntityCodeFolder(String boPackage) {
        //接口类路径基础
        String iPath = generateConfig.getGenerateAddress()+ File.separator +"src" + File.separator + "main" + File.separator + "java" + File.separator;
        String[] ss = boPackage.split("\\.");
        String packPath = "";
        for(int a = 0; a < ss.length; a++){
            iPath = iPath + ss[a] +  File.separator;
            if(packPath.equals("")){
                packPath = ss[a];
            }else{
                packPath = packPath + File.separator+ ss[a];
            }
        }

        return iPath.substring(0, iPath.lastIndexOf(File.separator)) + ".java";
    }

    private Map<String, Object> getGenerateInfo(String tableName, List<FieldDto> fieldDtos) {
        String packageName = generateConfig.getPackageName();

        String tblprefix = tableName.substring(tableName.indexOf("_") + 1);
        String objectName = ObjStrUtls.getObjNam(tblprefix);

        Map<String, Object> context = new HashMap();
        context.put("projectModel", packageName);
        context.put("className", objectName);
        context.put("littleClassName", objectName.substring(0, 1).toLowerCase() + objectName.substring(1, objectName.length()));
        context.put("DtoDesc", "A entity for the {@link " + objectName + "} entity.");
        return context;
    }

    private static List<Map<String, String>> getFields(Class cs) {
        List<Map<String, String>> fieldList = new ArrayList();
        Field[] fields = cs.getDeclaredFields();
        for (Field field : fields) {

            if ("serialVersionUID".equalsIgnoreCase(field.getName())) {
                continue;
            }
            Map<String, String> fieldMap = new HashMap();
            String fieldName = field.getName();
            fieldMap.put("name", fieldName);
            fieldMap.put("upperName", fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length()));
            fieldMap.put("type", field.getType().getSimpleName());
            fieldMap.put("typeFilter", field.getType().getSimpleName() + "Filter");
            if (fieldName.equalsIgnoreCase("id")) {
                fieldMap.put("buildSpecification", "buildSpecification");
            } else if (field.getType().getSimpleName().equalsIgnoreCase("string")) {
                fieldMap.put("buildSpecification", "buildStringSpecification");
            } else if (field.getType().getSimpleName().equalsIgnoreCase("long")) {
                fieldMap.put("buildSpecification", "buildRangeSpecification");
            } else if (field.getType().getSimpleName().equalsIgnoreCase("Instant")) {
                fieldMap.put("buildSpecification", "buildRangeSpecification");
            } else {
                fieldMap.put("buildSpecification", "buildSpecification");
            }

            fieldList.add(fieldMap);
        }
        return fieldList;
    }

    public String renderContent(Map<String, Object> context, String type) {
        StringWriter writer = new StringWriter();

        Template template = getTemplate(type);
        try {
            template.process(context, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取模板
     *
     * @param name
     * @return
     */
    private Template getTemplate(String name) {
        try {
            Template temp = configuration.getTemplate(name);
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过表名检索,获取字段信息
     * @param tableName 表明
     * @return 字段信息列表
     */
    public List<FieldDto> retrieveFieldsByTablename(String tableName) {
        List<FieldDto> fieldList = new ArrayList<FieldDto>();
        List<ColumnDTO> columnDTOList = codeMakerDao.queryColumnsByTableName(tableName);
        if(columnDTOList != null) {
            for(ColumnDTO column : columnDTOList) {
                if ("id".equals(column.getColumnName().toLowerCase())) {
                    continue; //排除主键
                }
                FieldDto fDto = new FieldDto();
                fDto.setName(ColumnUtils.formatField(column.getColumnName()));
                fDto.setFieldDbName(column.getColumnName());
                fDto.setFieldLength(String.valueOf(column.getColumnLength()));
                if(column.getColumnComment() != null) {
                    fDto.setFieldComment(column.getColumnComment());
                } else {
                    fDto.setFieldComment("TODO:添加注释");
                }

                fDto.setFieldPrecision(String.valueOf(column.getColumnPrecision()));
                fDto.setFieldScale(String.valueOf(column.getColumnScale()));
                fDto.setType(ColumnUtils.convertFieldType(column.getColumnType().toLowerCase(), String.valueOf(column.getColumnPrecision()), String.valueOf(column.getColumnScale())));
                fDto.setFieldConstraint(column.getColumnConstraint());
                fieldList.add(fDto);
            }
        }

        return fieldList;
    }
}
