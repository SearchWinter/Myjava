package annotation.processor;

import java.lang.reflect.Field;


/**
 * @ClassName FruitInfoUtil
 * @Description 注解处理器
 * @Author Li Anjun
 * @Date 2020/7/9  19:00
 **/
public class FruitInfoUtil {
    public static void main(String[] args) {
        util.getFruitInfo(Apple.class);
        System.out.println("*******");
        util.getFruitNum(Apple.class);
    }
}
