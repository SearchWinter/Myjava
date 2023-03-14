import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description if后面可以不接else, 只有当if(true)的时候才会执行
 * if()中是一个方法时，哪怕结果为false,（）中的方法还是会执行;list作为参数，值已经改变 @Date 2020/8/25 14:17
 */
public class TestDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        if (!putDemo(list)) {
            System.out.println("if条件为false,作为参数的list的值，会改变");
        }
        System.out.println(list);
    }

    public static boolean putDemo(List<Integer> list) {
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        return true;
    }

    @Test
    public void randomTest() {
        Random random = new Random();
        for (int j = 0; j < 10; j++) {
            int i = random.nextInt(20);
            System.out.println(i);
        }
    }

    @Test
    public void UTest1() throws ParseException {
        String beforeNDay = SelfUtils.getBeforeNDay("20201104", 2);
        System.out.println(beforeNDay);
    }

    /** for ()  if()后不接else*/
    @Test
    public void IFContinue() {
        for (int i = 0; i < 10; i++) {
            //
            if (i == 5) {
                continue;
            }
            System.out.println(i);
        }
    }

    @Test
    public void testNull() {
        System.out.println("taf" + null);
    }

    @Test
    public void math() {
        System.out.println(Math.round(Math.random() * 1000000));
    }

    @Test
    public void testMD5() throws NoSuchAlgorithmException {
        String demo = SelfUtils.getMD5("demo");
        System.out.println(demo);

        String demo1 = DigestUtils.md5Hex("demo");
        System.out.println(demo1);
    }

    @Test
    public void testPrint() {
        System.out.println("demo\rjava");
        System.out.println("demo\r\njava");
        System.out.println("demo");
    }

    @Test
    public void testGC(){
        for (int i = 0; i <1000 ; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
        }
    }


    @Test
    public void getBeforeDays() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        ArrayList<String> arrayList = new ArrayList<>();

        String day="20210615";
        Date date = dateFormat.parse(day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for(int i=0;i<30;i++) {
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            String beforeDay = dateFormat.format(calendar.getTime());
            arrayList.add(beforeDay);
        }

        System.out.println(arrayList);
    }
}
