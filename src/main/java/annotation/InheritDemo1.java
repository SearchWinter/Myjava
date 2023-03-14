package annotation;

/**
 * @ClassName InheritDemo1
 * @Description 在InheritDemo1中使用自己定义的注解
 * @Author Li Anjun
 * @Date 2020/7/9  16:03
 **/
//赋值的方式是在注解的括号内以 value="" 形式，多个属性之前用 ，隔开。
@MyAnnotation1(id=1,name="zhangsan")
public class InheritDemo1 {
    public static void main(String[] args) {
        /** A.isAnnotationPresent(B)  注释B是否在此A上。如果在则返回true；不在则返回false。*/
        boolean B=InheritDemo1.class.isAnnotationPresent(MyAnnotation1.class);
        System.out.println(B);
    }
    public static void show(){
        System.out.println("被Inherited标注的Annotation具有继承性");
    }
}
