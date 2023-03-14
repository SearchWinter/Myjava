package interfacedemo;

/**
 * @ClassName DefaultMethod
 * @Description TODO
 * @Author Li Anjun
 * @Date 2020/7/8  10:01
 **/
public class DefaultMethod implements DefaultDemo {
    public static void main(String[] args) {
        //接口里面的静态方法，使用InterfaceName.StaticMethod来调用
        DefaultDemo.hello();

        //接口添加默认方法，不用修改实现类。在实现类里面直接使用
        DefaultMethod defaultMethod = new DefaultMethod();
        System.out.println(defaultMethod.setTime("10:00 am"));
    }
}
