package com.maybe.util;

import java.io.FileOutputStream;
import java.util.List;

import com.maybe.pojo.BusLine;
import com.maybe.pojo.LngAndLat;
import com.maybe.pojo.Station;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 将信息写入excel文件
 */
public class WriteListToExcelFile {

    //线路经纬度
    public static void writeLineToFile(String fileName, BusLine busLine) throws Exception {
        Workbook workbook = null;
        workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("线路经纬度");


        int rowIndex = 0;
        List<Station> stations = busLine.getStations();
        String str = busLine.getLine_name();
        int int0 = str.lastIndexOf("(");
        int int1 = str.lastIndexOf("-");
        int int2 = str.lastIndexOf(")");

        String lineName = str.substring(0, int0);
        String startStation = str.substring(int0 + 1, int1);
        String endStation = str.substring(int1 + 1, int2);


        for (LngAndLat lngAndLat : busLine.getLine()) {
            Row row = sheet.createRow(rowIndex++);
            Cell cell0 = row.createCell(2);
            cell0.setCellValue(lineName);
            Cell cell1 = row.createCell(5);
            cell1.setCellValue(startStation);
            Cell cell2 = row.createCell(6);
            cell2.setCellValue(endStation);

            Cell cell3 = row.createCell(7);
            cell3.setCellValue(lngAndLat.getLng());
            Cell cell4 = row.createCell(8);
            cell4.setCellValue(lngAndLat.getLat());
        }
        // lets write the excel data to file now
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos);
        fos.close();
        System.out.println(fileName + " written successfully");
    }

    //站点经纬度
    public static void writeStationToFile(String fileName, BusLine busLine) throws Exception {
        Workbook workbook = null;

        if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new Exception("invalid file name, should be xls or xlsx");
        }

        Sheet sheet = workbook.createSheet("站点经纬度");


        int rowIndex = 0;
        List<Station> stations = busLine.getStations();
        String str = busLine.getLine_name();
        int int0 = str.lastIndexOf("(");
        int int1 = str.lastIndexOf("-");
        int int2 = str.lastIndexOf(")");

        String lineName = str.substring(0, int0);
        String startStation = str.substring(int0 + 1, int1);
        String endStation = str.substring(int1 + 1, int2);


        for (Station station : stations) {
            Row row = sheet.createRow(rowIndex++);
            Cell cell0 = row.createCell(2);
            cell0.setCellValue(lineName);
            Cell cell1 = row.createCell(5);
            cell1.setCellValue(startStation);
            Cell cell2 = row.createCell(6);
            cell2.setCellValue(endStation);

            Cell cell3 = row.createCell(11);
            cell3.setCellValue(station.getName());
            Cell cell4 = row.createCell(12);
            cell4.setCellValue(station.getPosition().getLng());
            Cell cell5 = row.createCell(13);
            cell5.setCellValue(station.getPosition().getLat());
        }
        // lets write the excel data to file now
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos);
        fos.close();
        System.out.println(fileName + " written successfully");
    }


    //excel文件增加一行
    //上下行，线路名，经度，纬度
    public static void writeInfoToFile(String fileName, String[] stationInfo) throws Exception {
        Workbook workbook = null;

        workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("站点经信息");

        Row row = sheet.createRow(0);


        for (int i = 0; i < stationInfo.length; i++) {
            Cell cell0 = row.createCell(i);
            cell0.setCellValue(stationInfo[i]);
        }
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos);
        fos.close();
        System.out.println(fileName + " written successfully");
    }
}
