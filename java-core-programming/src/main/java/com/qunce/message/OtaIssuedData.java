package com.qunce.message;

import lombok.Data;

@Data
public class OtaIssuedData {
    /**
     *  01表示启动主板升级，其他启动从板升级
     */
    private String MainBoardOrSlaveBoard;
    /**
     *  固件地址偏移
     */
    private String offset;
    /**
     *  固件数据
     */
    private String firmware;
    /**
     *  下发固件的总长度
     */
    private String totalLength;
    /**
     *  下发固件的CRC16校验和
     */
    private String firmwareCRC16;
}
