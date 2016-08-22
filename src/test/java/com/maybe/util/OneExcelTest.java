package com.maybe.util;

import org.junit.Test;

/**
 * Created by Maybe on 2016/7/21
 * Maybe has infinite possibilities
 */
public class OneExcelTest {

    @Test
    public void testMerge() throws Exception {//线路经纬度
        String from = "C:\\Users\\Administrator\\Desktop\\零碎0";
        String to = "C:\\Users\\Administrator\\Desktop\\望京区域.xlsx";
        OneExcel oneExcel = new OneExcel();
        oneExcel.merge(from, to);
    }
}