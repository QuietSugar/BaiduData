package com.maybe.util;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Maybe on 2016/7/6
 * Maybe has infinite possibilities
 */
public class selectBusLine {
    @Test
    public void selectBusLineByName() {
        Connection connection = ConnectionFactory.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        if (connection == null) {
            System.out.println("创建连接失败");
        }
        String sql = "SELECT * FROM busLine WHERE lineName LIKE  ?";
        System.out.println(sql);
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%运通114%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String lineName = resultSet.getString(1);
                String lineVale = resultSet.getString(2);
                System.out.println(lineName);
                System.out.println(lineVale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeResources(resultSet, preparedStatement, connection);
        }

    }
}
