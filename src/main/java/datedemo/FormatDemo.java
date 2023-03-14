package datedemo;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Date 2020/9/9  13:58
 **/
public class FormatDemo {
    public static void main(String[] args) throws ParseException {
        String beforeDay = getBeforeDay("20200808");
        System.out.println(beforeDay);

        ArrayList<String> beforeDay1 = getBeforeDay(3);
        System.out.println(beforeDay1);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String date = simpleDateFormat.format(1602737796233L);
        System.out.println(date);
    }

    //得到指定日期，前一天的日期
    public static String getBeforeDay(String ds) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(ds));

        cal.add(Calendar.DAY_OF_MONTH, -1);

        return sdf.format(cal.getTime());
    }

    /**
     * 近n天的日期,保存到list中
     * */
    public static ArrayList<String> getBeforeDay(Integer n) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        ArrayList<String> list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        //从0开始，包括当天
        for(int i=0;i<=n;i++) {
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_MONTH, -i);
            list.add( sdf.format(cal.getTime()));
        }
        return list;
    }
    /**
     * 将时间戳转换为指定格式的字符串  long->String
     * SimpleDateFormat类的format()方法
     */
    @Test
    public void dataFormat1(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long l = System.currentTimeMillis();
        String date = simpleDateFormat.format(l);
        System.out.println(date);
    }

    /**
     * parse() 解析特定格式的字符串 String 2020-09-30 ->Date
     * @throws ParseException
     */
    @Test
    public void parseString() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse("2020-09-30");
        System.out.println(parse);
    }

    @Test
    /** 时间转换为时间戳
     *  getTime()
     * */
    public void dateFormat2() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse("20200920");
        long time = date.getTime();
    System.out.println(time);
    }
    @Test
    public void dateFormat3() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2020-12-02 23:22:23");
        long time = date.getTime();
        System.out.println(time);
    }

    @Test
    /**
     * 获取四天前的日期，指定格式
     */
    public void dateDemo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH,-4);

        String s = sdf.format(cal.getTime());
        System.out.println(s);
    }

    @Test
    public void test(){
        System.out.println(new Date());
    }
    //output
    //Mon Dec 28 10:47:34 CST 2020

    /** 东八区时间转换为0时区
     * JVM默认情况下获取的就是操作系统的时区设置
     * GMT:格林威治标准时间
     * UTC:世界协调时间
     * DST:夏日节约时间
     * CST:中国标准时间
     * **/
    @Test
    public void testZoneA() throws ParseException {
        String dateStr="2020-12-28 08:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //解析字符串，时区：东八时区
        Date date = format.parse(dateStr);
        System.out.println(date.getTime());
        //格式化日期 0时区
        //设置时区
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println(format.format(date));
    }
    //output
    //1609113600000
    //2020-12-28 00:00:00

    /** 带时区转换*/
    @Test
    public void testZoneB() throws ParseException {
        String dateStr="2020-12-28 10:00:00+0800";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        //解析字符串，时区：东八区
        Date date = dateFormat.parse(dateStr);
        //格式化日期，时区：0时区
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println(dateFormat.format(date));
    }
    //output
    //2020-12-28 02:00:00+0000

    /** 带TZ格式的时间解析*/
    @Test
    public void testTZ() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(df.parse("2014-08-23T09:20:05Z").toString());
    }
    @Test
    public void testZoneC() throws ParseException {
        String dateStr="2020-12-28 11:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = dateFormat.parse(dateStr);
        System.out.println(date);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println(dateFormat.format(date));
    }

    @Test
    public void testFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = dateFormat.format(new Date());
        System.out.println(format);
    }

    @Test
    public void testFormat2() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        String str1="20210128";
        String str2="2360";
        Date parse = format.parse(str1 + str2);
        System.out.println(parse);
    }

    /** 验证Calendar的add方法*/
    @Test
    public void testCalendar() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String sDay="20210401";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(sDay));
        System.out.println("calendar1："+calendar.getTime().getTime());

        //DAY_OF_MONTH是一个int的变量，值为5
        calendar.add(Calendar.DAY_OF_MONTH,+1);
        System.out.println("calendar2："+calendar.getTime().getTime());

        calendar.add(Calendar.DAY_OF_MONTH,-1);
        System.out.println("calendar2："+calendar.getTime().getTime());

    }
}
