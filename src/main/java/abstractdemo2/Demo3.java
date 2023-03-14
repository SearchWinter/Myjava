package abstractdemo2;

import abstractdemo.StaticDemo1;

/**
 * Created by anjunli on  2022/10/14
 **/
public class Demo3 {
    public static void main(String[] args) {
        //不同包，无法通过父类实例化，访问protected属性和方法
        StaticDemo1 staticDemo1 = new StaticDemo1();
//        System.out.println(staticDemo1.sum);

        //不同包，子类继承的protected属性和方法只能在子类内部调用
        Demo2 demo2 = new Demo2();
//        System.out.println(demo2.sum);
//        demo2.setSum(10);
    }
}
