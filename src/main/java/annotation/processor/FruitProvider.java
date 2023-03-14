package annotation.processor;

import java.lang.annotation.*;

/** 水果供应商的注解*/

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitProvider {
    /**供应商编号*/
    public int id() default -1;
    /**供应商名称*/
    public String name() default "";
    /**供应商地址*/
    public String address() default "";
}
