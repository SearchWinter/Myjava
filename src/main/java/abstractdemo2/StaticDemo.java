package abstractdemo2;

import abstractdemo.StaticDemo1;
import annotation.processor.FruitNum;

import static abstractdemo.StaticDemo1.display;
import static abstractdemo.StaticDemo1.*;

/**
 * @ClassName StaticDemo
 * @Description 验证静态变量的各种使用，
 * @Date 2020/6/29  18:43
 **/
public class StaticDemo {
    static  int num2=198989;
    public static void main(String[] args) {
        //在不同包中也可以直接调用公有的静态方法
        StaticDemo1.display();

//        静态方法中无法调用，非静态方法；生成该类的对象，通过对象来调用非静态方法。
//        feat();
//        feat();
//        StaticDemo staticDemo = new StaticDemo();


//        staticDemo.feat();
        //同名
        System.out.println(num2);

        System.out.println(StaticDemo1.num2);

        //protected  不同包不能够访问
//        System.out.println(new StaticDemo1().sum);
    }
    public void feat(){
        System.out.println("非静态方法");
    }

    public void feat(String name){

    }
}
