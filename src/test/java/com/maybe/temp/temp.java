package com.maybe.temp;

import com.maybe.pojo.StationDT;
import com.maybe.util.ConnectionFactory;
import com.maybe.util.StationDTUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hadoop on 2016/9/13.
 */
public class temp {
    @Test
    public void test() {
        String sql = "SELECT XIANLUHAO,ZHANDIANMINGCHENG,LNG,LAT FROM A_GONGJIAOZHANHAO WHERE XIANLUHAO = '519' AND SHANGXIAXINGFLAG = 0 ORDER BY SHUNXUHAO";
        Connection connection = ConnectionFactory.getConnectionOracle();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            String str = "new Array(";

            while (resultSet.next()) {
                String lineNum = resultSet.getString("XIANLUHAO");
                String stationName = resultSet.getString("ZHANDIANMINGCHENG");
                double lng = resultSet.getDouble("LNG");
                double lat = resultSet.getDouble("LAT");
                str += "[" + lng + "," + lat + ",\"" + lineNum + "\",\"" + stationName + "\"],";
            }
            str = str.substring(0, str.lastIndexOf(","));
            str += ");";
            System.out.println(str);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Test
    public void test01() {
        StationDT stationDT = new StationDT();
        stationDT.setLineName("5555");
        stationDT.setStartStation("从哪儿来");
        stationDT.setEndStation("到那儿去");
        stationDT.setDistance(456);
        stationDT.setTime(567);
        StationDTUtil.insertStationDT(stationDT);
    }

}
