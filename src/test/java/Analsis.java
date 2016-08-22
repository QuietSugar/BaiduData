import com.maybe.util.ExcelUtil;
import com.maybe.util.GetName;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 读取文件内容，比较已存在的文件
 * Created by Maybe on 2016/7/14
 * Maybe has infinite possibilities
 */
public class Analsis {
    @Test
    public void run() {
        String filePath = "C:\\Users\\Administrator\\Desktop\\test.xlsx";//文件路径
        String filesPath = "C:\\Users\\Administrator\\Desktop\\line";//文件夹路径

        GetName getName = new GetName();
        String[] strArr = getName.getFileName(filesPath);//文件夹下面所有文件的名字
        List<String> list0 = new ArrayList<String>();//读取到文件中的信息
        List<String> list1= new ArrayList<String>();//有差别
        Set<String> set = new HashSet<String>();
        System.out.println(strArr[2]);

        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.setStartReadPos(1);
        excelUtil.setOnlyReadOneSheet(false);
        List<Row> rowList = new ArrayList<Row>();
        try {
            rowList = excelUtil.readExcel_xlsx(filePath);

            for (Row row : rowList) {
                list0.add(row.getCell(0).toString());
            }
        } catch (IOException e) {
            System.out.println("读取异常");
            e.printStackTrace();
        }

        //开始比较
        for (int i = 0; i < strArr.length; i++) {
            set.add(strArr[i]);


        }
        for (String string : list0) {
            if (set.add(string)) {

                list1.add(string);
            }

        }
        System.out.println("以下是差别-----------");
        for (String s : list1) {
            System.out.println(s);
        }


    }


}
