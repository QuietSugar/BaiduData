package com.maybe.util;

import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Maybe on 2016/7/13
 * Maybe has infinite possibilities
 */
public class main {
    public static void main(String[] args) {
        //读取线路
        ExcelUtil eu = new ExcelUtil();
        eu.setStartReadPos(1);
        String src_xlspath = "C:\\Users\\Administrator\\Desktop\\公交线路缺少站点经纬度1.xlsx";


        List<Row> rowList = new ArrayList<Row>();
        try {
            rowList = eu.readExcel(src_xlspath);
            //eu.writeExcel_xls(rowList, src_xlspath, dist_xlsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<String> testSet = new HashSet<String>();
        System.out.println(rowList == null);
        System.out.println(rowList.get(0).getCell(0));
        System.out.println(rowList.get(4).getCell(0));

        for (Row row : rowList) {
            if (row.getCell(0) == null) {
                System.out.println("单元格为空");
            } else {
                testSet.add( row.getCell(0).toString() + "(" + row.getCell(3).toString() + "-" + row.getCell(4).toString() + ")");
//                testSet.add("set.put(\"" + row.getCell(0).toString() + "-" + row.getCell(3).toString() + "-" + row.getCell(4).toString() + "\");");
//                testSet.add("busline.getBusList(\"" + row.getCell(0).toString() + "\");");
            }
        }
        System.out.println("------------------------------------------------------------------------");
        for (String str : testSet) {
            System.out.println(str);
        }
    }
}
