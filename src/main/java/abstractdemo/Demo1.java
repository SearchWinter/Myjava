package abstractdemo;

/**
 * @Description  protected 同一个包内都可以访问
 * @Date 2020/7/3  9:20
 **/
public class Demo1 extends StaticDemo1{
    public static void main(String[] args) {
        StaticDemo1 staticDemo1 = new StaticDemo1();
        System.out.println(staticDemo1.sum);
        int sum = staticDemo1.sum;
        staticDemo1.setSum2(10);

        Demo1 demo1 = new Demo1();
        System.out.println(demo1.sum);
        demo1.setSum2(10);
    }

    @Override
    void setSum2(int sum) {
        super.setSum2(sum+10);
    }
}
