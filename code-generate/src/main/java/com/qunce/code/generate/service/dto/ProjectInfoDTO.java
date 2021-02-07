package com.qunce.code.generate.service.dto;

import java.io.Serializable;

public class ProjectInfoDTO implements Serializable {
    private static final long serialVersionUID = 5662995867565314676L;
    private String apiBundleName;
    private String bundleName;
    private String tableName;
    private String boBundleName;
    private String boPackage;
    private String dtoBundleName;
    private String dtoPackage;
    private String objectName;
    private String objDesc;

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBoBundleName() {
        return boBundleName;
    }

    public void setBoBundleName(String boBundleName) {
        this.boBundleName = boBundleName;
    }

    public String getBoPackage() {
        return boPackage;
    }

    public void setBoPackage(String boPackage) {
        this.boPackage = boPackage;
    }

    public String getDtoBundleName() {
        return dtoBundleName;
    }

    public void setDtoBundleName(String dtoBundleName) {
        this.dtoBundleName = dtoBundleName;
    }

    public String getDtoPackage() {
        return dtoPackage;
    }

    public void setDtoPackage(String dtoPackage) {
        this.dtoPackage = dtoPackage;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjDesc() {
        return objDesc;
    }

    public void setObjDesc(String objDesc) {
        this.objDesc = objDesc;
    }

    public String getApiBundleName() {
        return apiBundleName;
    }

    public void setApiBundleName(String apiBundleName) {
        this.apiBundleName = apiBundleName;
    }

}
