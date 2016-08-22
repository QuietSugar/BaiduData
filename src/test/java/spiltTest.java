import org.junit.Test;

/**
 * Created by Maybe on 2016/7/13
 * Maybe has infinite possibilities
 */
public class spiltTest {
    @Test
    public void test() {
        String str = "是师生(我是括号-里面的内容)dddswef";
        int int0 = str.indexOf("(");
        int int1 = str.indexOf("-");
        int int2 = str.lastIndexOf(")");

        System.out.println(str.substring(0,int0));
        System.out.println(str.substring(int0+1,int1));
        System.out.println(str.substring(int1+1,int2));
    }
}
