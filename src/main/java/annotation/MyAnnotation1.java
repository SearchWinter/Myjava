package annotation;

import java.lang.annotation.*;

/**
 * @author anjunli
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Inherited
public @interface MyAnnotation1 {
    /** 注解只有成员变量，没有成员方法。成员变量以“无形参的方法”形式来声明
     *  方法名定义成员变量的名称，方法返回值类型定义了成员变量的类型
     * */
    int id();
    String name();
}

