package interfacedemo.args;

/**
 * Created by anjunli on  2021/2/20
 **/
public class Test2 implements Demo {
    @Override
    public int display(int a) {
        System.out.println("Test2对参数进行加法运算");
        return a+100;
    }
}
