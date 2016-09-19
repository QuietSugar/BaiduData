package com.maybe.temp;

import com.maybe.pojo.LngAndLat;
import com.maybe.pojo.StationDT;
import com.maybe.util.ConnectionFactory;
import com.maybe.util.StationDTUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void test02() {
        //查询所有线路名称
        String sql = "SELECT XIANLUMINGCHENG FROM A_GONGJIAOZHANHAO GROUP BY XIANLUMINGCHENG";
        Connection connection = ConnectionFactory.getConnectionOracle();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> list = new ArrayList<String>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            String lineName = null;
            while (resultSet.next()) {
                lineName = resultSet.getString("XIANLUMINGCHENG");
                list.add(lineName);
            }
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement = null;
            resultSet = null;
        }
        String str = "new Array(";
        for (String lineName : list) {
            str += "\"" + lineName + "\",";
        }
        str = str.substring(0, str.lastIndexOf(","));
        str += ");";
        System.out.println(str);

        List<double[]> listUp = new ArrayList<>();
        List<double[]> listDown = new ArrayList<>();
        System.out.println("线路总共有" + list.size() + "条");
        for (int i = 0; i < 1; i++) {
            String sql1 = "SELECT XIANLUMINGCHENG,ZHANDIANMINGCHENG,SHANGXIAXINGFLAG,LNG,LAT FROM A_GONGJIAOZHANHAO WHERE XIANLUMINGCHENG = ?  ORDER BY SHANGXIAXINGFLAG, SHUNXUHAO";
            try {
                preparedStatement = connection.prepareStatement(sql1);
                preparedStatement.setString(1, list.get(i));
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.getInt("SHANGXIAXINGFLAG") == 0) {
                        listUp.add(new double[]{resultSet.getDouble("LNG"), resultSet.getDouble("LAT")});
                    } else {
                        listDown.add(new double[]{resultSet.getDouble("LNG"), resultSet.getDouble("LAT")});
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (double[] doubleArray : listUp) {
            System.out.print(doubleArray[0] + "----");
            System.out.println(doubleArray[1]);
        }
        System.out.println("--邪恶的的分割线---------------");

        for (double[] doubleArray : listUp) {
            System.out.print(doubleArray[0] + "----");
            System.out.println(doubleArray[1]);
        }

    }

}
