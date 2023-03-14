package reflect;

import org.junit.Test;

/**
 * Created by anjunli on  2020/12/1
 **/
public class TestClass {
    /** 基本数据类型的class对象，及包装类的class对象*/
    @Test
    public void test(){
        Class<Integer> c1 = Integer.class;
        Class<Integer> c2 = int.class;

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c1==c2);

        //包装类中有一个字段TYPE,是一个引用，指向对应的基本数据类型的Class  int.class=Integer.TYPE
        Class<Integer> type = Integer.TYPE;
        System.out.println(type);
        System.out.println(c2==type);
    }
}
