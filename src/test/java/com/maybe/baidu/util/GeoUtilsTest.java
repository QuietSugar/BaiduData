package com.maybe.baidu.util;

import com.maybe.baidu.pojo.Bounds;
import com.maybe.baidu.pojo.Circle;
import com.maybe.baidu.pojo.Point;
import com.maybe.baidu.pojo.Polyline;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * 测试类
 *
 * Created by Maybe on 2016/9/6.
 * Maybe has infinite possibilities
 */
public class GeoUtilsTest {

    private Point myPoint = new Point(116.01,40.02);

    private Point point1 = new Point(116.0,40.0);
    private Point point2 = new Point(116.1,40.0);
    private Point point3 = new Point(116.1,40.1);
    private Point point4 = new Point(116.0,40.1);

    //两地大概距离：弧长=8949214   弦长=8231403
    private Point pointBeiJing =  new Point(39.915833333,116.39055555555);
    private Point pointSydney =  new Point(-33.8599722222,151.21111111111);

    @Test
    public void testIsPointInRect() throws Exception {
        System.out.println(new GeoUtils().isPointInRect(myPoint,new Bounds(point1,point3)));
    }

    @Test
    public void testIsPointInCircle() throws Exception {
        System.out.println(new GeoUtils().isPointInCircle(myPoint,new Circle(1,point1)));

        System.out.println(new GeoUtils().getDistance(pointBeiJing,pointSydney));
    }

    @Test
    public void testIsPointOnPolyline() throws Exception {
        Point testPoint = new Point(116.05,40.05);
        ArrayList<Point> path = new ArrayList<>();
        path.add(point1);
        path.add(point2);
        path.add(point3);
        path.add(point4);
        System.out.println(new GeoUtils().isPointOnPolyline(point1,new Polyline(path)));
    }

    @Test
    public void testIsPointInPolygon() throws Exception {

    }

    @Test
    public void testGetDistance() throws Exception {

    }
}