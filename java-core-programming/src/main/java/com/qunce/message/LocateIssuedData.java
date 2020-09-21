package com.qunce.message;

import lombok.Data;

@Data
public class LocateIssuedData {
    /**
     * 标签序列号
     */
    private String tagSN;
    /**
     * 标签位置
     */
    private String tagLol;
    /**
     * 异常状态
     */
    private String state;
}
