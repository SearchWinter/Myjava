import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkContext;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by kivenchen on 2016/3/9.
 */
public class SelfUtils {

    public static String getBeforeDay(String ds) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(ds));

        cal.add(Calendar.DAY_OF_MONTH, -1);

        return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
    }

    public static String getBeforeNDay(String ds, int n) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(ds));

        cal.add(Calendar.DAY_OF_MONTH, 0-n);

        return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
    }

    public static String getAfterDay(String ds) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(ds));

        cal.add(Calendar.DAY_OF_MONTH, 1);

        return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
    }

    public static String getAfterNDay(String ds, int n) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(ds));

        cal.add(Calendar.DAY_OF_MONTH, n);

        return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
    }

    public static String getBeforeMonth(int iBefore) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, iBefore);

        return new SimpleDateFormat("yyyyMM").format(c.getTime());
    }

    public static String getBeforeMonth(String ds, int iBefore) throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(ds));
        cal.add(Calendar.MONTH, iBefore);

        return new SimpleDateFormat("yyyyMM").format(cal.getTime());
    }

    public static boolean isLastDayOfWeek(String ds) throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(ds));

        if(cal.get(Calendar.DAY_OF_WEEK) == 7) {
            return true;
        }
        return false;
    }

    public static boolean isLastDayOfMonth(String ds) throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(ds));

        if(cal.getActualMaximum(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }

        return false;
    }

    public static List<String> getDaysOfMonth(String m) {
        if (m.length() != 6) {
            return null;
        }
        List<String> dayList = new ArrayList<>();

        int year = Integer.parseInt(m.substring(0, 4));
        int month = Integer.parseInt(m.substring(4,6)) - 1;
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        int num = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i <= num; ++i) {
            String dStr = m;
            if (i < 10) {
                dStr += "0" + i;
            }
            else {
                dStr += i;
            }
            dayList.add(dStr);
        }

        return dayList;
    }

    public static List<String> getDaysOfThisWeek(String lastDay) throws Exception {
        if (lastDay.length() != 8) {
            return null;
        }
        List<String> dayList = new ArrayList<>();

        for (int i = 6; i > 0; --i) {
            dayList.add(getBeforeNDay(lastDay, i));
        }
        dayList.add(lastDay);
        return dayList;
    }

    public static List<String> getDaysOfLastWeek(String lastDay) throws Exception {
        if (lastDay.length() != 8) {
            return null;
        }
        List<String> dayList = new ArrayList<>();

        for (int i = 13; i >= 7; --i) {
            dayList.add(getBeforeNDay(lastDay, i));
        }
        return dayList;
    }

    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    //判断hdfs路径是否存在
    public static boolean isHdfsPathExist(SparkContext sparkContext, String sHdfsPath) throws Exception {
        Configuration conf = sparkContext.hadoopConfiguration();

        FileSystem fs = FileSystem.get(conf);

        return fs.exists(new Path(sHdfsPath));
    }

    //删除hdfs中的文件
    public static void deleteHdfsPath(SparkContext sparkContext, String sHdfsPath) throws Exception {
        Configuration conf = sparkContext.hadoopConfiguration();

        FileSystem fs = FileSystem.get(conf);

        Path filePath = new Path(sHdfsPath);

        fs.delete(filePath, true);
    }

    public static String getUTF8String(String s) {
        try
        {
            return new String(s.getBytes("utf-8"));
        }
        catch(Exception e)
        {
            System.out.print(e.getStackTrace());
        }

        return "";
    }

    /** md5*/
    public static String getMD5(String  idString) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(idString.getBytes());
        String md5Code = new BigInteger(1, md5.digest()).toString(16);
        return md5Code;
    }

    /** commons获取MD5值*/
    public static String getMD52(String idString){
        return DigestUtils.md5Hex(idString);
    }

    /** 判读字符串是否为空*/
    public static boolean strIsEmpty(String s) {
        if (s == null) return true;
        return s.trim().isEmpty();
    }
}
