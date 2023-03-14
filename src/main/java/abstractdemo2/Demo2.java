package abstractdemo2;

import abstractdemo.StaticDemo1;

/**
 * @Date 2020/7/3  9:18
 * @Description protected
 * 不同包的子类继承了父类的属性和方法，但是只能在子类的内部进行访问
 **/
public class Demo2 extends StaticDemo1 {
    public static void main(String[] args) {
        //不同包，无法通过父类实例化，访问protected属性和方法
        StaticDemo1 staticDemo1 = new StaticDemo1();
//        System.out.println(staticDemo1.sum);

        Demo2 demo2 = new Demo2();
        System.out.println(demo2.sum);
        demo2.setSum(10);
        System.out.println(demo2.sum);
    }
}
