package annotation.defaultvalues;

import java.lang.annotation.*;

/**
 * 自定义一个注解，其注解成员变量有默认值
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation2 {
    int id() default 100;
    String name() default "lisi";
}
