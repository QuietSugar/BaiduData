package com.maybe.servlet;

import com.maybe.pojo.LngAndLat;
import com.maybe.pojo.Station;
import com.maybe.pojo.StationDT;
import com.maybe.util.StationDTUtil;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        System.out.println(request.getParameter("data"));
        System.out.println(request.getParameter("lineName"));
        System.out.println(request.getParameter("flag"));

//
        ObjectMapper mapper = new ObjectMapper();
        List<Station> stations = new ArrayList<>();
        stations.add(new Station("001", "朱辛庄", new LngAndLat(0.0, 0.0)));
        stations.add(new Station("002", "二拨子", new LngAndLat(1.0, 1.0)));
        stations.add(new Station("003", "安宁庄北路", new LngAndLat(2.0, 2.0)));

        String str = mapper.writeValueAsString(stations);

        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(str);
        printWriter.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
