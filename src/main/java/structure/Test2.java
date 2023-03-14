package structure;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;

/** Created by anjunli on 2020/11/4 */
public class Test2 {

  @Test
  //数组方法测试
  public void demo() {
    String[] REPORT_FILED_NAME = {
            "ip", "time", "guid", "app_id", "msg_id", "msg_status", "param", "feedback_time", "xua"
    };
    System.out.println(REPORT_FILED_NAME[0]);
  }

  @Test
  public void testList() {
    String[] strings = {"var1", "var2", "var3"};
    System.out.println();

    /** list重写了toString方法*/
    ArrayList<String> list = new ArrayList<>();
    list.add("var1");
    list.add("var2");
//        String string = list.toString();
    System.out.println(list);
  }

  /**  得到字符串的hash值*/
  @Test
  public void hashTest(){
    int hashTest = Objects.hash("hashTest");
    System.out.println(hashTest);
  }
}
