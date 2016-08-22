package com.maybe.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 合并excel成为一个文件
 * Created by Maybe on 2016/7/18
 * Maybe has infinite possibilities
 */
public class OneExcel {
    /**

     * 合并方法
     */
    public void merge(String from, String to) {
        Workbook workbook = null;
        //最终写成一个.xlsx文件
        workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("公交信息");
        int rowIndex = 0;

        //第一层循环，获取文件绝对路径
        GetName getName = new GetName();
        String[] strArr = getName.getFileName(from);
        ExcelUtil eu = new ExcelUtil();
        List<Row> list = new ArrayList<Row>();
        for (String string : strArr) {
            System.out.println(from + "//" + string + ".xlsx");
            try {
                list = eu.readExcel(from + "//" + string + ".xlsx");
                for (Row row0 : list) {
                    Row row = sheet.createRow(rowIndex++);

                    // 获取每一单元格的值
                    for (int j = 0; j < row0.getLastCellNum(); j++) {
                        String value = getCellValue(row0.getCell(j));
                        if (!value.equals("")) {
                            Cell cell = row.createCell(j);
                            cell.setCellValue(getCellValue(row0.getCell(j)));
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("------异常---------------------");
            } finally {
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(to);
            workbook.write(fos);
            fos.close();
            System.out.println(to + " written successfully");
        } catch (IOException e) {
            System.out.println("-----------出现异常---------");
        }
    }

    /***
     * 读取单元格的值
     *
     * @param cell
     * @return
     * @Title: getCellValue
     * @Date : 2014-9-11 上午10:52:07
     */
    private String getCellValue(Cell cell) {
        Object result = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    result = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    result = cell.getNumericCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    result = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    result = cell.getCellFormula();
                    break;
                case Cell.CELL_TYPE_ERROR:
                    result = cell.getErrorCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                default:
                    break;
            }
        }
        return result.toString();
    }
}
