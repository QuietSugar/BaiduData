package com.maybe.servlet;

import com.maybe.pojo.StationDT;
import com.maybe.util.DataSourceUtil;
import com.maybe.util.StationDTUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maybe on 2016/7/1
 * Maybe has infinite possibilities
 */
@WebServlet(name = "/StationDistanceAndTimeServlet", urlPatterns = "/StationDistanceAndTimeServlet")
public class StationDistanceAndTimeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String data = request.getParameter("data");
        ObjectMapper mapper = new ObjectMapper();

        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, StationDT.class);
        List<StationDT> lst = (List<StationDT>) mapper.readValue(data, javaType);

        //数据库操作，插入数据库
        Connection conn = null;
        try {
            conn = StationDTUtil.getDataSource().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取连接失败-------------------------");
        }
        PreparedStatement preparedStatement = null;
        String sql = "INSERT  INTO A_GONGJIAOZHANINFOTEMP3 VALUES(?,?,?,?,?)";
        try {
            preparedStatement = conn.prepareStatement(sql);


            for (StationDT stationDT : lst) {
                System.out.println(stationDT.getLineName() + "-----" + stationDT.getStartStation() + "-----" + stationDT.getEndStation()
                        + "---------" + stationDT.getDistance() + "-----" + stationDT.getTime() + "-----");
                preparedStatement.setString(1, stationDT.getLineName());
                preparedStatement.setString(2, stationDT.getStartStation());
                preparedStatement.setString(3, stationDT.getEndStation());
                preparedStatement.setInt(4, stationDT.getDistance());
                preparedStatement.setInt(5, stationDT.getTime());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
