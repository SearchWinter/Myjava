package num;

import org.junit.Test;
import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by anjunli on  2020/12/11
 **/
public class MathTest {
    @Test
    public void test1(){
        Long total =13434L;
        Long count=23L;

        String str = total + "|" + count;
        System.out.println(str);
    }

    /** 随机数*/
    @Test
    public void randomTest(){
        System.out.println(Math.random());
    }

    /** 四舍五入 Math.floor(x+0.5)*/
    @Test
    public void roundTest(){
        System.out.println(Math.round(12.3));
        System.out.println(Math.round(12.5));
        System.out.println(Math.round(-12.3));
        System.out.println(Math.round(-12.5));
    }

    /** 向上取整*/
    @Test
    public void ceilTest(){
        System.out.println(Math.ceil(12.3));
        System.out.println(Math.ceil(-12.3));
    }

    /** 向下取整*/
    @Test
    public void floorTest(){
        System.out.println(Math.floor(12.3));
        System.out.println(Math.floor(-12.3));
    }

    /** 小数转换为百分比*/
    @Test
    public void percentTest(){
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        //保留两位小数
        numberFormat.setMaximumFractionDigits(2);
        System.out.println(numberFormat.format(0.1234));

        System.out.println(numberFormat.format(Double.valueOf(11)/Double.valueOf(20)));
    }
}
