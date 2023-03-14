package thread.create.exThread;

/**
 * @Description TODO
 * @Date 2020/7/30  11:16
 **/
public class TestDemo {
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo("Thread extends 1");
        Thread thread = new Thread(threadDemo, threadDemo.getThreadName());
        thread.start();
    }
}
