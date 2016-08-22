import com.maybe.pojo.LngAndLat;
import org.junit.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maybe on 2016/8/8
 * Maybe has infinite possibilities
 */
public class LLannalsisi {
    @Test
    public void test() {
        BufferedReader bufferedReader = null;
        List<LngAndLat> line = new ArrayList<LngAndLat>();
        double maxLng = 0;
        double minLng = 500;
        double maxLat = 0;
        double minLat = 500;
        try {
//            bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\0801\\回龙观边界-arcgis.txt"));
            bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\0801\\望京-arcgis.txt"));
            String str;
            String lng;
            String lat;
            while ((str = bufferedReader.readLine()) != null) {
                int n = str.indexOf(",");
                lng = str.substring(0, n);
                lat = str.substring(n + 1);
                double lngValue = Double.parseDouble(lng);
                double latValue = Double.parseDouble(lat);

                if (lngValue > maxLng)
                    maxLng = lngValue;

                if (lngValue < minLng)
                    minLng = lngValue;


                if (latValue > maxLat)
                    maxLat = latValue;

                if (latValue < minLat)
                    minLat = latValue;

                line.add(new LngAndLat(lngValue, latValue));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("经度最大值" + maxLng + "     最小值" + minLng);
        System.out.println("纬度最大值" + maxLat + "     最小值" + minLat);

        for (LngAndLat lngAndLat : line) {
            System.out.print("["+lngAndLat.getLng()+","+lngAndLat.getLat()+"],");
        }

    }
}
