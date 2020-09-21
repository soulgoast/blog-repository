package com.qunce.message;

import lombok.Data;

@Data
public class OtaUploadData {
	/**
	 * 01表示主板应答成功，其他从板应答成功
	 */
	private String MainBoardOrSlaveBoard;
	/**
	 * 启动升级，成功返回success,失败返回failure
	 */
	private String FOTABegin;
	/**
	 * 升级块的偏移
	 */
	private String offset;
	/**
	 * 下发数据，成功返回success,失败返回failure
	 */
	private String FOTAUpdate;
	/**
	 * 升级完成，成功返回success,失败返回failure
	 */
	private String FOTAFinished;
}
