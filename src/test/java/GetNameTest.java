import com.maybe.util.GetName;
import org.junit.Test;

/**
 * Created by Maybe on 2016/7/18
 * Maybe has infinite possibilities
 */
public class GetNameTest {
    @Test
    public void getName() {
        GetName getName = new GetName();
        String[] strArr = getName.getFileName("C:\\Users\\Administrator\\Desktop\\line\\处理好的站点经纬度7.20");
        for (String string : strArr) {
            int first = string.indexOf("(");
            System.out.print(string.substring(0, first) + ",");
        }
    }
}
