/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.poi.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;

import java.io.*;

/**
 * @ClassName Poi01WorkBook
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/7/28 15:35
 * @ModifyDate 2020/7/28 15:35
 * @Version 1.0
 */
public class Poi01WorkBook {

    @Test
    public void createHSSFWorkBook() {
        Workbook workbook = new HSSFWorkbook();
        try (FileOutputStream outputStream = new FileOutputStream("hssf.xls")) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createXSSFWorkBook() {
        Workbook workbook = new XSSFWorkbook();
        try (OutputStream outputStream = new FileOutputStream("xssf.xlsx")) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void newSheet() {
        Workbook workbook = new HSSFWorkbook();
        /**
         * 注意Excel的工作表名称不能超过31个字符
         * 也不能包含以下字符：
         * 0x0000
         * 0x0003
         * 冒号：
         * 反斜杠\
         * 星号 *
         * 正斜杠 /
         * 问号？
         * 中括号[]
         */
        Sheet sheet1 = workbook.createSheet("new sheet");
        Sheet sheet2 = workbook.createSheet("second sheet");

        /**
         * 当工作表的名称不能确定时，可以通过WorkBookUtil来生成一个符合规范的名称
         * 使用空格代替非法字符
         */
        // Sheet sheet3 = workbook.createSheet("[aaa]"); // java.lang.IllegalArgumentException: Invalid char ([) found at index (0) in sheet name '[aaa]'
        String safeSheetName = WorkbookUtil.createSafeSheetName("/aaf?*bvc[xiaox]");
        System.out.println(safeSheetName);
        Sheet sheet3 = workbook.createSheet(safeSheetName);

        /**
         * 工作表名称不能重复
         */
        // Sheet sheet3 = workbook.createSheet("new sheet");

        try (OutputStream outputStream = new FileOutputStream("workbook.xls")) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void createCell() {
        Workbook wb = new HSSFWorkbook();
//Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");
// Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(0);
// Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue(1);
// Or do it on one line.
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(
                createHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);
// Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addCommont() throws IOException {
        // 创建工作簿对象
        XSSFWorkbook wb = new XSSFWorkbook();
        // 创建工作表对象
        XSSFSheet sheet = wb.createSheet("我的工作表");
        // 创建绘图对象
        XSSFDrawing p = sheet.createDrawingPatriarch();
        // 创建单元格对象,批注插入到4行,1列,B5单元格
        XSSFCell cell = sheet.createRow(4).createCell(1);
        // 插入单元格内容
        cell.setCellValue(new XSSFRichTextString("批注"));
        // 获取批注对象
        // (int dx1, int dy1, int dx2, int dy2, short col1, int row1, short
        // col2, int row2)
        // 前四个参数是坐标点,后四个参数是编辑和显示批注时的大小.
        XSSFComment comment = p.createCellComment(new XSSFClientAnchor(0, 0, 0,0, (short) 3, 3, (short) 5, 6));
        // 输入批注信息
        comment.setString(new XSSFRichTextString("这是批注内容!"));
        // 添加作者,选中B5单元格,看状态栏
        comment.setAuthor("toad");
        // 将批注添加到单元格对象中
        cell.setCellComment(comment);
        // 创建输出流
        FileOutputStream out = new FileOutputStream("writerPostil.xlsx");

        wb.write(out);
        // 关闭流对象
        out.close();
    }

}
