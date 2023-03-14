package annotation.processor;

import java.lang.annotation.*;

/** 水果数量的注解*/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitNum {
    public int sum() default 12919;
    public int sellNum() default 0;
}
