package com.qunce.message;

import lombok.Data;

import java.util.List;

@Data
public class IssuedMessage<T> {

	/**
	 * 设备编号
	 */
	private String deviceNo;
	/**
	 * 厂商标识
	 */
	private String corpID;
	/**
	 * 时间戳
	 */
	private String timeStamp;
	/**
	 * 协议号
	 */
	private String protocolNo;
	/**
	 * 消息内容
	 */
	private List<T> DATA;
}
