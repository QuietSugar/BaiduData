package com.maybe.servlet;

import com.maybe.pojo.LngAndLat;
import com.maybe.pojo.Station;
import com.maybe.pojo.StationDT;
import com.maybe.util.ConnectionFactory;
import com.maybe.util.StationDTUtil;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Maybe on 2016/9/17
 * Maybe has infinite possibilities
 */
@WebServlet(name = "/GetStationByLineNameAndFlagServlet", urlPatterns = "/GetStationByLineNameAndFlagServlet")
public class GetStationByLineNameAndFlagServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--邪恶的分割线-----------");
        request.setCharacterEncoding("UTF-8");
        String lineName = request.getParameter("lineName");
        String flag = request.getParameter("flag");
        System.out.println(lineName + "----" + flag);
//
        ObjectMapper mapper = new ObjectMapper();

        List<double[]> list = new ArrayList<>();
//        list.add(new double[]{116.328325, 40.077114});
//        list.add(new double[]{116.336276, 40.079226});
//        list.add(new double[]{116.333634, 40.08318});


        Connection connection = ConnectionFactory.getConnectionOracle();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT XIANLUMINGCHENG,ZHANDIANMINGCHENG,SHANGXIAXINGFLAG,LNG,LAT FROM A_GONGJIAOZHANHAO WHERE XIANLUMINGCHENG = ? AND SHANGXIAXINGFLAG = ?  ORDER BY  SHUNXUHAO";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, lineName);
            preparedStatement.setString(2, flag);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new double[]{resultSet.getDouble("LNG"), resultSet.getDouble("LAT")});
            }

            if("1".equals(flag)){
                Collections.reverse(list);//反序排列
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeResources(resultSet,preparedStatement, connection);
        }

        String str = mapper.writeValueAsString(list);

        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(str);
        printWriter.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
