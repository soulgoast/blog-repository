package com.qunce.code.generate.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProjectInfoDTO implements Serializable {
    private static final long serialVersionUID = 5662995867565314676L;
    private String apiBundleName;
    private String bundleName;
    private String tableName;
    private String tableNameCn;
    private String boBundleName;
    private String boPackage;
    private String dtoBundleName;
    private String dtoPackage;
    private String objectName;
    private String objDesc;


}
