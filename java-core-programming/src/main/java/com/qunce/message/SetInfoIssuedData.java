package com.qunce.message;

import lombok.Data;

@Data
public class SetInfoIssuedData {
    /**
     * 参数配置偏移长度/固件地址偏移
     */
    private String offset;
    /**
     * read_length
     */
    private String read_length;
    /**
     * upload_time_interval
     */
    private String upload_time_interval;
    /**
     * 配置版本号
     */
    private String setVersion;
    /**
     * mqtt用户名
     */
    private String username;
    /**
     * mqtt密码
     */
    private String password;
}
