package com.maybe.util;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 读取Excel文件，以.xls结尾
 * Created by Maybe on 2016/4/27.
 */
public class ExcelReader {
    private XSSFWorkbook wb;
    private XSSFSheet sheet;
    private XSSFRow row;

    /**
     * @param path       文件路径
     * @param sheetIndex 工作簿下标
     * @param rowIndex   结束行下标
     * @param colIndex   结束列下标
     * @return
     */
    public String readOneCell(String path, int sheetIndex, int rowIndex, int colIndex) {
        String[][] array = readGrid(path, sheetIndex, rowIndex, rowIndex, colIndex, colIndex);
        return array[0][0];
    }

    /**
     * @param path          文件路径
     * @param sheetIndex    工作簿下标
     * @param beginRowIndex 开始行下标
     * @param endRowIndex   结束行下标
     * @param beginColIndex 开始列下标
     * @param endColIndex   结束列下标
     * @return 返回String数组
     */
    public String[][] readGrid(String path, int sheetIndex, int beginRowIndex, int endRowIndex, int beginColIndex, int endColIndex) {
        try {
            InputStream is = new FileInputStream(path);
            wb = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(sheetIndex);
        int totalRowNum = endRowIndex - beginRowIndex + 1;
        int totalColNum = endColIndex - beginColIndex + 1;
        String[][] array = new String[totalRowNum][totalColNum];
        String cell;
        for (int i = 0; i < totalRowNum; i++) {
            String[] rowArray = new String[totalColNum];
            row = sheet.getRow(i + beginRowIndex);
            for (int j = 0; j < totalColNum; j++) {
                cell = getCellFormatValue(row.getCell(j + beginColIndex)).trim();
                rowArray[j] = cell;
            }
            array[i] = rowArray;
        }
        return array;
    }

    /**
     * @param cell
     * @return
     */
    private String getCellFormatValue(XSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                case XSSFCell.CELL_TYPE_BLANK: {
//                    System.out.println("空白");
                    break;
                }
                // 如果当前Cell的Type为NUMERIC(数值)FORMULA(公式)
                case XSSFCell.CELL_TYPE_NUMERIC:
                case XSSFCell.CELL_TYPE_FORMULA: {
//                    System.out.println("数值或公式");
                    // 判断当前的cell是否为Date
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);
                    }
                    // 如果是纯数字
                    else {
                        Double d = cell.getNumericCellValue();
                        //判断小数部分是否为0，此方法涉及浮点计算，小数位数太多时并不准确
                        if (d % 1 == 0) {
                            cellvalue = String.format("%.0f", d);
                        } else {
                            BigDecimal bigDecimal = new BigDecimal(d);
                            cellvalue = bigDecimal.toString();
                        }
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case XSSFCell.CELL_TYPE_STRING:
//                    System.out.println("字符");
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    System.out.println("未识别类型");
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }
}
