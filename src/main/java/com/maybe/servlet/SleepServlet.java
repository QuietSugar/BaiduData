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
 * Created by Maybe on 2016/9/20
 * Maybe has infinite possibilities
 */
@WebServlet(name = "/SleepServlet", urlPatterns = "/SleepServlet")
public class SleepServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long defaultSleepTime = 1000;

        String sleepTime = request.getParameter("sleepTime");
        if (!(sleepTime == null || sleepTime.length() == 0)) {
            defaultSleepTime = Long.parseLong(sleepTime);
        }
        try {
            Thread.sleep(defaultSleepTime);
            System.out.println("暂停成功----------时间:   " + defaultSleepTime + "----------------------------------");
        } catch (InterruptedException e) {
            System.out.println("暂停失败---------------------------------------------");
            e.printStackTrace();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write("success");
        printWriter.flush();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
