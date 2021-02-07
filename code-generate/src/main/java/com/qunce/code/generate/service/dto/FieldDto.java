package com.qunce.code.generate.service.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description
 * @Param 
 * @return 
 **/
@Data
@Accessors(chain = true)
public class FieldDto implements Serializable {
    private static final long serialVersionUID = 3025757740198998100L;
    private String name;        //字段名称
    private String type;        //字段类型
    private String fieldComment;    //字段注释
    private String fieldLength;        //字段长度
    private String fieldDbName;        //字段对应数据列名称
    private String fieldDbType;        //字段对应数据列名称
    private String fieldPrecision;    //字段精度
    private String fieldScale;        //字段小数位
    private String fieldCri;        //查询条件
    private String fieldbldCri;        //构造条件

}
