package exceptiondemo.userdefinedexception;

/**
 * @ClassName MyException
 * @Description 自定义异常类，都是Throwable的子类
 * 1、要继承Exception
 * 2、重写父类的构造方法
 *
 * @Author Li Anjun
 * @Date 2020/7/9  9:38
 **/
public class MyException extends Exception{
    //重写父类的构造方法
    public MyException() {
        super();
    }

    public MyException(String message) {
        //把参数传递给Exception的带String参数的构造方法
        super(message);
    }
}
