package abstractdemo;

/**
 * @Description 一个类只能继承一个抽象类
 * @Date 15:05 2020/7/1
 **/
public class Employee extends AbstractEmployee {
    protected int num2 = 66998;
    static int score = 149;

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.show(100);
        employee.add(100, 200);

        //继承父类的display()方法
        String str="display";
        employee.display(str);

        //静态变量只能通过"类.静态变量名"调用，类的实例不能调用
        System.out.println(AbstractEmployee.num);
        //在同一类中，直接调用
        System.out.println(score);

        //非静态变量当该变量所在的类被实例化后，可通过实例化的类名直接访问。
        //在同一个类中
        System.out.println(employee.num2);

        //父类里面的公有的变量
        System.out.println(employee.goods);
        //两个名称相同的变量 覆盖 向下转型
        System.out.println(((AbstractEmployee) employee).num2);

        //这里是局部变量，直接调用
        int agg = 10;
        System.out.println(agg);
    }

    //继承抽象类的子类，必须实现抽象类的抽象方法
    @Override
    public void display(String name) {
        System.out.println("实现父类的抽象方法"+name);
    }

    public void test(){
        StaticDemo1 staticDemo1 = new StaticDemo1();
        //default 包内所有类中均可见（且可调用）
        int sum = staticDemo1.sum;
        staticDemo1.setSum2(10);
    }
}