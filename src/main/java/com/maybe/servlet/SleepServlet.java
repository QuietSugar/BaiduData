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
        try {
            Thread.sleep(10000);
            System.out.println("暂停成功----------------------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write("success");
        printWriter.flush();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
