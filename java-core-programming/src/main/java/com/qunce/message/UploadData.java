package com.qunce.message;

import lombok.Data;

@Data
public class UploadData {
	/**
	 * 标签序列号
	 */
	private String tagSN;
	/**
	 * 标签位置
	 */
	private String tagLol;
	/**
	 * 标签事件
	 */
	private String tagFlag;
	/**
	 * 标签占位数
	 */
	private String occupyNum;
	/**
	 * 设备状态控制应答
	 */
	private String Ctrlstate;
	/**
	 * 标签写入应答
	 */
	private String Tagstate;
	/**
	 * 机柜信息接收应答
	 */
	private String Rackstate;
}
