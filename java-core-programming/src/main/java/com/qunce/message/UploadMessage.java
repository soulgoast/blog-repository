package com.qunce.message;

import lombok.Data;

import java.util.List;

@Data
public class UploadMessage<T> {
	/**
	 * 设备序列号
	 */
	private String devSn;
	/**
	 * 时间戳
	 */
	private String timeStamp;
	/**
	 * 设备类型
	 */
	private String devType;
	/**
	 * 厂商标识
	 */
	private String corpID;
	/**
	 * 协议号
	 */
	private String protocolNo;
	/**
	 * 业务类型
	 */
	private String busiType;
	/**
	 * 主机版本号
	 */
	private String deviceMainVersion;
	/**
	 * 辅机版本号
	 */
	private String deviceOtherVersion;
	/**
	 * 从板个数
	 */
	private String slaveBoadNum;
	/**
	 * 消息内容
	 */
	private List<T> DATA;
}
