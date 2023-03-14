package extendsdemo;

import java.util.List;

/**
 * @Description 继承测试案例，多重继承
 * @Date 2020/7/1  9:45
 **/
public class Animal {
    public String name;
    private int id;
    //添加一个无参的构造方法
    public Animal() {
    }

    public Animal(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void eat(){
        System.out.println("Animal的eat方法"+name+"吃");
    }
    public void sleep(){
        System.out.println(name+"睡");
    }
    public void introduction() {
        System.out.println("大家好！我是"  + id + "号" + name + ".");
    }
    public static void Walk(){
        System.out.println("散步");
    }

    public void CharOne(char c1){
        System.out.println(c1);
    }
    public void CharDemo(char[] arr){
        System.out.println(arr[0]);
    }

    public void IntOne(int num){
        System.out.println(num);
    }
    public void IntDemo(int[] arr){
        System.out.println(arr[0]);
    }
    public void DoubleDemo(double[] arr){
        System.out.println(arr[0]);
    }
}
