package com.maybe.servlet;

import com.maybe.pojo.StationDT;
import com.maybe.util.StationDTUtil;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        StationDT stationDT = mapper.readValue(data, StationDT.class);
        StationDTUtil.insertStationDT(stationDT);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
