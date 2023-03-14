import org.junit.Test;

import java.text.ParseException;
import java.util.List;

/**
 * Created by anjunli on  2021/8/16
 **/
public class UtilTest {
    public static void main(String[] args) {

    }

    @Test
    public void testDay() throws ParseException {
        List<String> allDay = DropTable.getAllDay("20180101", "20191231");
        System.out.println(allDay);
    }
}
