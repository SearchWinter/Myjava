package interfacedemo;

/**
 * @Author Li Anjun
 * @Description TODO
 * @Date 17:31 2020/6/29
 * @Param
 * @return
 **/
public class InterfaceExtends implements Demo01,Demo2 {
    public static void main(String[] args) {
        InterfaceExtends interfaceExtends = new InterfaceExtends();
        interfaceExtends.display();
        interfaceExtends.show();

//        被隐式指定为public static final,值是不能够再次修改的
//        Demo01.num=200;
//        Demo2.num=300;

        //两个接口里面有相同名称的变量，使用"类名.静态变量名"调用
        System.out.println(Demo2.num);
        System.out.println(Demo01.num);


//        继承的两个接口，都有display()方法
//        如果参数都一样的话，那么调用任意一个效果都是相同的,在重写的时候也只能重写一个display()方法
//        参数不一样，那就构成了重载，可以通过传入参数的类型来确定调用那个方法
        interfaceExtends.display();
        interfaceExtends.display("goods",20);
    }

    @Override
    public void show() {
        System.out.println();
    }

    @Override
    public void display(String name, int age) {
        System.out.println("这是Demo01的display方法"+name+"---"+age);
    }

    @Override
    public void display() {
        System.out.println("这是display方法");
    }

}


