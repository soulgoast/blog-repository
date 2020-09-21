/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @ClassName ExcelUtils
 * @Description TODO
 * 导入Excel文件（支持“XLS”和“XLSX”格式）
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/11/15 16:53
 * @ModifyDate 2019/11/15 16:53
 * @Version 1.0
 */
public class ExcelUtils {

    public static Excel getExcel(String fileName, int headerNum) throws IOException, InvalidFormatException {
        return getExcel(new File(fileName), headerNum);
    }

    public static Excel getExcel(File file, int headerNum) throws IOException, InvalidFormatException {
        return getExcel(file, headerNum, 0);
    }

    public static Excel getExcel(File file, int headerNum, int sheetIndex) throws IOException, InvalidFormatException {
        return getExcel(file.getName(), new FileInputStream(file), headerNum, sheetIndex);
    }

    public static Excel getExcel(String fileName, InputStream is, int headerNum, int sheetIndex)
            throws InvalidFormatException, IOException {
        return null;

    }
}

class Excel {

    private static Logger log = LoggerFactory.getLogger(Excel.class);

    /**
     * 工作薄对象
     */
    private Workbook wb;

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 标题行号
     */
    private int headerNum;


}