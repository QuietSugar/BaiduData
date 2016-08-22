package com.maybe.servlet;

import com.maybe.pojo.BusLine;
import com.maybe.util.WriteListToExcelFile;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Maybe on 2016/7/1
 * Maybe has infinite possibilities
 */
@WebServlet(name = "/BaiduDataServlet", urlPatterns = "/BaiduDataServlet")
public class BaiduDataServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String stationDataPath = rootPath + "BusData//station//";
        String lineDataPath = rootPath + "BusData//line//";

        request.setCharacterEncoding("UTF-8");
        String lineBusNameString = request.getParameter("line_name");
        String lineBusValueString = request.getParameter("line_value");
        ObjectMapper mapper = new ObjectMapper();
        BusLine busLine = mapper.readValue(lineBusValueString, BusLine.class);

        //站点信息
        String str = busLine.getLine_name();
//        int int0 = str.lastIndexOf("(");
//        int int1 = str.lastIndexOf("-");
//        int int2 = str.lastIndexOf(")");
//        String lineName = str.substring(0, int0);
//        String startStation = str.substring(int0 + 1, int1);
//        String endStation = str.substring(int1 + 1, int2);
//
//        System.out.println("线路名称：" + lineName + "---起点：" + startStation + "---终点：" + endStation + "---公司：" + busLine.getCompany());

        File stationFilePath = new File(stationDataPath);
        File lineFilePath = new File(lineDataPath);
        if (!stationFilePath.exists()) {
            stationFilePath.mkdirs();
        }
        if (!lineFilePath.exists()) {
            lineFilePath.mkdirs();
        }

        String busName = busLine.getLine_name();
        String fileName = stationDataPath + busName + ".xlsx";
        String fileName_Line = lineDataPath + busName + ".xlsx";
        try {
            WriteListToExcelFile.writeStationToFile(fileName, busLine);
            WriteListToExcelFile.writeLineToFile(fileName_Line, busLine);
        } catch (Exception e) {
            System.out.println("失败了");
            e.printStackTrace();
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.write("success");
        printWriter.flush();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
