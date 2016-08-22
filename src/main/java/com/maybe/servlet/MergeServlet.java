package com.maybe.servlet;

import com.maybe.util.OneExcel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by Maybe on 2016/7/26
 * Maybe has infinite possibilities
 */
@WebServlet(name = "/MergeServlet", urlPatterns = "/MergeServlet")
public class MergeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH-mm");
        String now = simpleDateFormat.format(new Date());

        OneExcel oneExcel = new OneExcel();
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        File mergeFilePath = new File(rootPath+"BusData//合并");
        if (!mergeFilePath.exists()) {
            mergeFilePath.mkdirs();
        }

        String lineFrom = rootPath + "BusData//line//";
        String lineTo =mergeFilePath+ "//合并线路" + now + ".xlsx";

        String stationFrom = rootPath + "BusData//station//";
        String stationTo =mergeFilePath+ "//合并站点" + now + ".xlsx";


        oneExcel.merge(stationFrom, stationTo);
        oneExcel.merge(lineFrom, lineTo);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
