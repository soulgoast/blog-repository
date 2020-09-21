package com.qunce.message;

import lombok.Data;

@Data
public class BoxInfoIssuedData {
    /**
     *  标签序列号
     */
    private String tagSN;
    /**
     *  标签位置
     */
    private String tagLol;
    /**
     *  占位数目
     */
    private String occupyNum;
    
}
