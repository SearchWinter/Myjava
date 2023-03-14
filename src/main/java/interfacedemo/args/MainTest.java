package interfacedemo.args;

/**
 * Created by anjunli on  2021/2/20
 * 可以将接口类型的参数作为方法参数，在实际是使用时可以将实现了接口的类传递给方法，方法按照重写的原则执行，实际调用的是实现类中的方法代码体
 * 这样便根据传入的参数的不同而实现不同的功能。
 * 重要的是，当我以后还需要实现新的不同的功能的时候，只需新的类实现借口，然后传递给方法即可。
 *
 **/
public class MainTest {
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        Test2 test2 = new Test2();

        //执行Test1里面的display方法
        System.out.println(run(test1));
        //执行Test2里面的display方法
        System.out.println(run(test2));
    }

    //方法传入的参数是接口
    public static int run(Demo demo){
        int display = demo.display(10);
        return display;
    }
}
