package exceptiondemo.userdefinedexception;

import java.util.Scanner;

/**
 * @ClassName MyExceptionTest
 * @Description 自定义异常类的测试类
 * @Author Li Anjun
 * @Date 2020/7/9  9:45
 **/
public class MyExceptionTest {
    public static void main(String[] args) {
           Scanner scanner=new Scanner(System.in);
        int score=scanner.nextInt();

        //使用自己定义的异常类来捕获异常
        try {
            chick(score);
        } catch (MyException e) {
            e.printStackTrace();
        }

    }
    //检查分数合法性的方法,抛出自己的异常类
    public static void chick(int score) throws MyException{
        if(score>150||score<0){
            //分数不合法时抛出异常，new一个自己的异常类
            throw new MyException("分数是不合法的，应该在0-150之间");
        }else{
            System.out.println("分数是合法的，你的分数是"+score);
        }
    }

}
