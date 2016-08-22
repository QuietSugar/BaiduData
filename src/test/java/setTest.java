import com.maybe.util.FinalStatic;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Maybe on 2016/7/13
 * Maybe has infinite possibilities
 */
public class setTest {
    @Test
    public void test() {
        FinalStatic finalStatic = new FinalStatic();
        HashSet<String> hashSet = (HashSet<String>) (finalStatic.getSet());

        for(String str :hashSet){
            System.out.println(str);
        }

    }
}
