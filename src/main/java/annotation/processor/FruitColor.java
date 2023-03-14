package annotation.processor;

import java.lang.annotation.*;


/** 注解里面可以使用enum类型，成员变量的值使用枚举值*/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface FruitColor {
    //颜色枚举
    public enum Color{BULE,RED,GREEN};

    //颜色属性
    Color fruitColor() default Color.BULE;

}
