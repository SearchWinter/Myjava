package regex.patterndemo;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description java.util.regex.pattern各种方法的实例
 * @Date 2020/7/15  10:28
 **/
public class PatternTest {

    /**
     * Pattern.matches(regex,input)    Boolean
     * 尝试对整个目标字符串进行匹配，也就是说只有整个目标字段完全匹配时才会返回true
     */
    @Test
    public void matchesTest() {
        String s = "aawwssiijj123fsf";
        //只匹配到了数字，返回false
        System.out.println(Pattern.matches("\\d{3,}", s));
        //匹配字母数字下划线，整个字段都匹配到了，返回true
        String s1 = "aawwssiijj123fsf";
        System.out.println(Pattern.matches("\\w*", s1));
    }

    /**
     * matcher(input)      matcher
     * 创建匹配器，它将根据此模式匹配给定的输入。
     */
    @Test
    public void matcherTest() {
        String input = "java hello java scala";
        Pattern pattern = Pattern.compile("java");
        Matcher match = pattern.matcher(input);
        while (match.find()) {
            System.out.println(match.group());
        }
    }

    //匹配多个子字符串
    @Test
    public void matcherTest2() {
        String input = "Push.ConnectServer_push_reportrst_20201102.log";
        Pattern pattern = Pattern.compile("(push_feedback_20)|(push_reportrst_20)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            System.out.println(true);
        }
    }

    /**
     * split(CharSequence input,int limit)         通过将输入分解为该模式的匹配来计算的字符串数组
     * 将给定的字符序列匹配后分割为指定模式
     * limit 控制模式应用的次数
     */
    @Test
    public void splitTest() {
        String string = "boo:and:foo";
        Pattern pattern = Pattern.compile("o");
        String[] split = pattern.split(string, 2);
        for (String str : split) {
            System.out.println(str);
        }
    }
    /* 1    {"boo:and:foo"}
       2    {"boo","and:foo"}
       3    {"boo","and","foo"}
    * */

    /**
     * split(CharSequence input)     就是根据指定的模式分割
     */
    @Test
    public void splitTest2() {
        String string = "boo:and:foo";
        Pattern pattern = Pattern.compile("o");
        String[] split = pattern.split(string);
        for (String str : split) {
            System.out.println(str);
        }
    }
    /* o    {"b",":and:f"}
       :    {"boo","and","foo"}
    * */

    /**
     * Pattern.MULTILINE       多行模式
     */
    @Test
    public void multilineTest() {
        //里面有\r\n
        String str = "hello world\r\n" + "hello java\r\n" + "hello java";
        Pattern pattern = Pattern.compile("^hello");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group() + "位置：" + matcher.start() + "," + matcher.end());
        }

        System.out.println("使用MULTILINE,多行匹配");
        Pattern pattern2 = Pattern.compile("^hello", Pattern.MULTILINE);
        Matcher matcher2 = pattern2.matcher(str);
        while (matcher2.find()) {
            System.out.println(matcher2.group() + "位置：" + matcher2.start() + "," + matcher2.end());
        }
    }

    @Test
    public void lognameTest() {
        Pattern pattern = Pattern.compile("[0-9]{8}");
        Matcher match = pattern.matcher("ATG.TradeServer_rankdata_20200812.log");
        while (match.find()) {
            System.out.println(match.group());
        }
    }
}
