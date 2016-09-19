package com.maybe.util;

import com.maybe.pojo.StationDT;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by hadoop on 2016/9/14.
 * Maybe has infinite possibilities
 */
public class StationDTUtil {
    private static DataSource dataSource = null;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            try {
                dataSource = DataSourceUtil.getDataSource(DataSourceUtil.DRUID_MYSQL_SOURCE);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("获取连接失败");
            }
        }


        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public synchronized static Boolean insertStationDT(StationDT stationDT) {
        Connection connection = ConnectionFactory.getConnectionOracle();
        String sql = "INSERT INTO A_GONGJIAOZHANINFO VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, stationDT.getLineName());
            preparedStatement.setString(2, stationDT.getStartStation());
            preparedStatement.setString(3, stationDT.getEndStation());
            preparedStatement.setInt(4, stationDT.getDistance());
            preparedStatement.setInt(5, stationDT.getTime());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.printf(e.toString());
            return false;
        } finally {
            ConnectionFactory.closeResources(preparedStatement, connection);
        }
    }
}
