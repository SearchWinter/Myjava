import java.util.Date;

/**
 * Created by anjunli on  2020/11/26
 * 值传递和引用传递
 **/
public class BaseTest {
    public static void main(String[] args) {
        int a=10;

        test1(a);
        System.out.println(a);

        Myobj myobj = new Myobj();
        test2(myobj);
        System.out.println(myobj.b);
    }

    public static void test1(int a){
        a=a++;
        System.out.println(a);
    }
    public static void test2(Myobj myobj){
        myobj.b=100;
        System.out.println(myobj.b);
    }
}
class Myobj{
    public int b=99;
}
