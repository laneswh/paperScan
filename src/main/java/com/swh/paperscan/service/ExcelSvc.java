/*
 * Copyright (c) 2021. Song Wenhao.
 * All rights reserved.
 */

package com.swh.paperscan.service;

import com.swh.paperscan.pojo.ScanRes;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.FileOutputStream;
import java.util.List;

public class ExcelSvc {

    public String doExport(String path, List<ScanRes> list) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("详细路径");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("文件名");
        cell.setCellStyle(style);
        int rowNum = 1;
        for (ScanRes scanRes : list) {
            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(scanRes.getFilePath());
            row.createCell(1).setCellValue(scanRes.getFileName());
            rowNum++;
        }
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        try {
            String filePath = path + "\\扫描结果" + System.currentTimeMillis() + ".xls";
            FileOutputStream fout = new FileOutputStream(filePath);
            wb.write(fout);
            fout.close();
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
