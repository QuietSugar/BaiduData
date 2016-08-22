package com.maybe.servlet;

import com.maybe.util.WriteListToExcelFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maybe on 2016/8/8
 * Maybe has infinite possibilities
 */
@WebServlet(name = "/GetInStationServlet", urlPatterns = "/GetInStationServlet")
public class GetInStationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("收到请求" + request.getParameter("stationInfo"));
        long l = System.currentTimeMillis();
        String stationInfo = request.getParameter("stationInfo").replaceAll("\"", "");
        String[] stationInfoArray = stationInfo.split(",");
        try {
            WriteListToExcelFile.writeInfoToFile("C:\\Users\\Administrator\\Desktop\\零碎0\\" + stationInfoArray[1] + stationInfoArray[0] +l+ ".xlsx", stationInfoArray);
        } catch (Exception e) {
            System.out.println("写excel文件异常");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
