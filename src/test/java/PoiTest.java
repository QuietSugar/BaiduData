import com.maybe.util.ExcelUtil;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maybe on 2016/7/13
 * Maybe has infinite possibilities
 */
public class PoiTest {
    @Test
    public void test() {
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.setStartReadPos(1);
        excelUtil.setOnlyReadOneSheet(false);
        List<Row> rowList = new ArrayList<Row>();
        try {
            rowList = excelUtil.readExcel_xlsx("C:\\Users\\Administrator\\Desktop\\test.xlsx");

//            for(Row row:rowList){
//                row.get
//            }
        } catch (IOException e) {
            System.out.println("读取异常");
            e.printStackTrace();
        }
    }
}
