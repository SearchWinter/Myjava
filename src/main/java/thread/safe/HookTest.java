package thread.safe;

/**
 * Created by anjunli on  2021/5/8
 * Java程序经常会遇到进程挂掉的情况，一些状态没有正确的保存下来，这时候就需要在JVM关掉的时候执行一些清理现场的代码
 **/
public class HookTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("thread1....");
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                System.out.println("thread2......");
            }
        };

        /*** 这个方法的意思就是在jvm中增加一个关闭的钩子，当jvm关闭的时候，会执行系统中已经设置的所有通过方法addShutdownHook添加的钩子，
         * 当系统执行完这些钩子后，jvm才会关闭。所以这些钩子可以在jvm关闭的时候进行内存清理、对象销毁、关闭连接等操作。*/
        Runtime.getRuntime().addShutdownHook(
                new Thread(){
                    @Override
                    public void run(){
                        System.out.println("thread shutDownHook....");
                    }
                }
        );

        thread1.start();
        thread2.start();
    }
}
