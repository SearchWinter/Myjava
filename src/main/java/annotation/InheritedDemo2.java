package annotation;

import java.lang.annotation.Inherited;

/**
 * @ClassName InheritedDemo2
 * @Description TODO
 * @Author Li Anjun
 * @Date 2020/7/9  16:06
 **/
public class InheritedDemo2 extends InheritDemo1 {
    public static void main(String[] args) {
        boolean b=InheritedDemo2.class.isAnnotationPresent(MyAnnotation1.class);
        System.out.println(b);
    }
}
