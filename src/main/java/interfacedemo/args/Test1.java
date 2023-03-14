package interfacedemo.args;

/**
 * Created by anjunli on  2021/2/20
 **/
public class Test1 implements Demo {

    @Override
    public int display(int a) {
        System.out.println("Test1中对参数进行乘法运算");
        return a*100;
    }
}
