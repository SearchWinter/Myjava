package string;

import jodd.util.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

/**
 * @Description java.lang.string中某些方法的测试 @Date 2020/8/4 10:32
 */
public class TestDemo {
    @Test
    public void testMd2Hax() {
        String string = "org.junit.Test";
        String s = DigestUtils.md2Hex(string);
        System.out.println(s);
    }

    @Test
    /** 截取字符串 */
    public void subTest() {
        String string = "org.junit";
        int i = string.indexOf(".");
        // 包括beginIndex 不包括endIndex
        String substring = string.substring(0, i);
        System.out.println(substring);
    }

    @Test
    /** 取指定字符后几位的值 */
    public void subTest2() {
        String str = "DeleteSignalDataFromCache_20201013.log";
        int i = str.lastIndexOf("_");
        String substring = str.substring(i + 1, i + 9);
        System.out.println(substring);
    }

    // 用字符串表示负数，- 也算一个字符
    @Test
    public void lengthTest() {
        String num = "-1";
        System.out.println(num.length());
    }

    @Test
    public void test() {
        String str = "DeleteSignalDataFromCache_20201013.log";
        int i = str.lastIndexOf("_");
        System.out.println(i);
        System.out.println(str.length() - i);
    }

    @Test
    public void subTestPath() {
        String str = "file:///taf/warehouse/result/BaseStat/";
        String substring = str.substring(7);
        System.out.println(substring);
    }

    //截取有空值的情况
    @Test
    public void testSplit() {
        //reportrst
        String logName =
                "192.168.0.18|2020-09-04 10:59:59|d42c733926bc385b0a53eb3aeb563c47|com.hummer.shcj.client.windows|3269943|15||SN=WIN_com.hummer.shcj.client.windows&VN=0_0.0.0_0_DD&RL=1920_1080&OS=6.2.9200&CHID=4501_4501&MN=oemhummer_shcj&SDK=1.0.1&MO=&VC=&RV=|1599188399";
        //feedback
        String logName2 = "192.168.0.18|2020-11-02 14:57:01|4d12d95dbf37671fed16da1c93f5826c|com.hummer.shcj.client.windows|3270035|1604300221|SN=WIN_com.hummer.shcj.client.windows&VN=0_1.0.0_171_DD&RL=1920_1080&OS&CHID=1100_1100&MN=oemhummer_shcj&SDK=1.0.1&MO=&VC=&RV=";
        String logName3 = "10.251.255.25|2020-05-29 00:00:03|SN=IOS_com.upchina.ios.zngp&VN=20042713_2.2.3_20042713_GA&MO=iPhone 8&VC=Apple&RL=0_0&RV=&OS=13.4.1&CHID=55026_55026&MN=teach|up2107651|1590681603723|wx|113.83.1.59|d832d3e76282c9bcd787a92578b054d0|EE8161D8-9D35-475E-B3AD-C725BBA23EBA";

        String[] split = logName3.split("\\|", -1);
        System.out.println(split.length);
        for (String str : split) {
            System.out.println(str);
        }
    }

    /**
     * limit 参数的作用
     * 0 尽可能多的切分，但是末尾为空的字符串会被舍弃
     * 正数 n 切分 n-1次，不会舍弃空字符串，返回数组的长度也就为n
     * 负数 尽可能多的切分，不舍弃空字符串
     */
    @Test
    public void splitTest() {
        String str1 = "a|b|c|d|e";
        String str2 = "a|b|c||";
        String str3="10.251.250.225|2019-04-01 00:19:15|SN=&VN=&MO=&VC=&RL=&RV=&OS=&CHID=&MN=&SDK=|vincent07653|1554049156325||113.65.131.174||";
//    String[] split = str1.split("\\|",2);
        String[] split = str3.split("\\|");

        System.out.println("split length:" + split.length);
        for (String string : split) {
            System.out.println("split:"+string);
        }
    }

    /**
     * trim()用于删除字符串头尾的空字符串
     */
    @Test
    public void trimTest() {
        String str = " trim test ";
        String trim = str.trim();
        System.out.println(trim);
        System.out.println("str length: " + str.length());
        System.out.println("trim length:" + trim.length());
        System.out.println("".trim().length());
    }

    /**
     * contains()判断一个字符串是否包含某个字符串
     * 方法返回true，当且仅当此字符串包含指定的char值序列
     */
    @Test
    public void containsTest() {
        String logName = "_taf_._trace___t_trace__20201225.log";
        boolean contains = logName.contains("20201225");
        System.out.println(contains);
    }

    /**
     * equals()  比较字符串
     */
    @Test
    public void emptyTest() {
        String str = "";
        System.out.println("".equals(""));
        System.out.println(str == null);
    }

    /**
     * 使用已有的方法判断空值情况
     */
    @Test
    public void emptyTest2() {
        String str1 = "";
        while (StringUtil.isEmpty(str1)) {

        }
    }

    @Test
    public void isEmptyTest() {
        String str = "";
        System.out.println(str.isEmpty());
        System.out.println(StringUtils.isEmpty(str));
        System.out.println(str.trim() == null);
    }

    /**
     * equalsIgnoreCase() 比较字符串，并忽略大小写
     */
    @Test
    public void equalsTest2() {
        String str = "java";
        System.out.println("JAVA".equalsIgnoreCase(str));
    }

    /**
     * . 分割
     */
    @Test
    public void test2() {
        String str = "12.12";
        String[] split = str.split("\\.", -1);
        for (String str1 : split) {
            System.out.println(str1);
        }
    }

    /**
     * 字符串拼接的各种方法
     */
    @Test
    public void testAdd() {
        String str1 = "12";
        String str2 = "12";
        System.out.println(str1 + "." + str2);
    }

    /** 字符串格式化*/
    @Test
    public void testAdd2() {
        int size = 10;
        String.format("kafka flush size=%d,time=%d", size, System.currentTimeMillis());
    }

    /** 线上日志切分，分析*/
    @Test
    public void testLog(){
        String str1="10.251.255.25|2021-03-29 21:19:16|stockpc|192d6c0a035adb58e893dab5c56dde82|SN=WIN_com.upgpt.client.windows&VN=2020123150_v4.7_467&RL=1680_1050&OS=6.2.9200&CHID=4005_4005&MN=stockpc&SDK=1.0.1&MO=&VC=&RV=|182.99.137.250|up1948310|3,pc_pro_take_time;PRO_TIME_ADS_LAST_TIME,3,1;PRO_TIME_APP_AFTERLOGIN_INIT_INI,0,1;PRO_TIME_APP_EXIT,578,1;PRO_TIME_APP_INIT,344,1;PRO_TIME_APP_INIT_BLOCKDATA,0,1;PRO_TIME_APP_INIT_CEF,31,1;PRO_TIME_APP_INIT_OLE,0,1;PRO_TIME_APP_LOAD_APPSYS,109,1;PRO_TIME_APP_LOGIN_HQSERVER,1141,1;PRO_TIME_APP_MAINFRMONDRAW,2906,1;PRO_TIME_APP_READ_PYMAP,172,1;PRO_TIME_APP_USEING_TIME,497,1;PRO_TIME_CREATE_MDI,437,1;PRO_TIME_FRAME_INIT,266,1;PRO_TIME_GET_REQUEST_5026_0,0,1;PRO_TIME_HQ_REQUEST_1306_2,6530,1;PRO_TIME_HQ_REQUEST_1311_65,157,1;PRO_TIME_HQ_REQUEST_1316_2,16,1;PRO_TIME_HQ_REQUEST_1341_13,0,1;PRO_TIME_HQ_REQUEST_1341_16,469,1;PRO_TIME_HQ_REQUEST_1341_2,110,1;PRO_TIME_HQ_REQUEST_15_6,189,1;PRO_TIME_HQ_REQUEST_1802_16,2405,1;PRO_TIME_HQ_REQUEST_1805_13,16,1;PRO_TIME_HQ_REQUEST_1805_2,610,1;PRO_TIME_HQ_REQUEST_2_6,31,1;PRO_TIME_HQ_REQUEST_33_0,0,1;PRO_TIME_HQ_REQUEST_33_6,0,1;PRO_TIME_HQ_REQUEST_4062_6,16,1;PRO_TIME_HQ_REQUEST_4_0,1072,1;PRO_TIME_HQ_REQUEST_5000_6,32,1;PRO_TIME_HQ_REQUEST_5012_2,593,1;PRO_TIME_HQ_REQUEST_5012_65,125,1;PRO_TIME_HQ_REQUEST_5012_80,250,1;PRO_TIME_HQ_REQUEST_5020_13,273798,1;PRO_TIME_HQ_REQUEST_5022_13,308565,1;PRO_TIME_HQ_REQUEST_5023_13,293253,1;PRO_TIME_HQ_REQUEST_5025_13,156,1;PRO_TIME_HQ_REQUEST_5026_0,275875,1;PRO_TIME_HQ_REQUEST_5027_13,125,1;PRO_TIME_HQ_REQUEST_5030_0,0,1;PRO_TIME_HQ_REQUEST_5_6,16,1;PRO_TIME_HQ_REQUEST_6002_0,0,1;PRO_TIME_HQ_REQUEST_6005_0,480844,1;PRO_TIME_HQ_REQUEST_6006_0,0,1;PRO_TIME_HQ_REQUEST_6009_2,301140,1;PRO_TIME_HQ_REQUEST_6009_65,10188,1;PRO_TIME_HQ_REQUEST_6009_80,128624,1;PRO_TIME_HQ_REQUEST_6011_0,4852,1;PRO_TIME_HQ_REQUEST_6012_0,2405,1;PRO_TIME_HQ_REQUEST_6012_13,6776,1;PRO_TIME_HQ_REQUEST_6013_0,0,1;PRO_TIME_HQ_REQUEST_6013_6,0,1;PRO_TIME_HQ_REQUEST_6110_2,6608,1;PRO_TIME_HQ_REQUEST_7003_0,0,1;PRO_TIME_LOGIN_SSO,156,1;PRO_TIME_MAINFRM_SHOW,3203,1;PRO_TIME_MDI_NAVI_CREATE,16,1;PRO_TIME_WHOLE_LOGIN,1297,1|3,pc_pro_use_flow;PRO_FLOW_APP_USEING_FLOW,2947,1|4,pc_upgpt_click;AB0401,1;AC0109,1;AC0305001,3;N0101001,1;N0101002,2;N0102006,1;N0102007,1;N0102008,1;N0102012,3;N0102117,1;N0102118,1|4,pc_upgpt_login;login,1;login_c,1;open,1|4,pc_upgpt_stock_switch;00003035,2;00003039,1;00003040,1;01600795,1;01603759,1;01605286,1;01605389,1";
        String str2="10.251.250.225|2021-03-29 21:20:24|stockpc|1b340d0ee45234173aef3896b59fb596|SN=WIN_com.uphummer.client.windows&VN=0_0_0_&RL=1920_1080&OS=6.1.7601&CHID=4508_4508&MN=hummer&SDK=1.0.1&MO=&VC=&RV=|117.167.33.204|guest2018|2,pc_upgpt_unistall,1";
        String crashLog="10.251.255.25|2021-03-28 23:01:59|00000000000000000000000000000000|SN=WIN_com.upgpt.client.windows&VN=2020123150_v4.7_467&RL=3440_1440&OS=6.2.9200&CHID=4008_4008&MN=stockpc&SDK=1.0.1&MO=&VC=&RV=|0|140168|0|1|124.240.11.242|314|1025||Windows 10 Enterprise Build 19042|1||";
        String[] split = crashLog.split("\\|", -1);
        for(String string:split){
            System.out.println(string);
        }
    }

    @Test
    public void tetsByte(){
        long l=1234567890L;
        byte[] lb = Bytes.toBytes(l);
        System.out.println(lb.length);

        String s = String.valueOf(l);
        byte[] sb = Bytes.toBytes(s);
        System.out.println(sb.length);


    }
}
