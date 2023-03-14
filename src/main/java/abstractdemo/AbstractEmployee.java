package abstractdemo;

/**
 * @Description 抽象类
 * @Date 2020/6/29  18:26
 **/
public abstract class AbstractEmployee {
    public int goods=100;
    public static int num=100;
    protected int num2=1200;

    public void show(int a){
        System.out.println("show方法"+a);
    }
    protected void add(int a,int b){
        System.out.println("相加的结果为："+(a+b));

    }
    //抽象方法
    public abstract void display(String name);
}
