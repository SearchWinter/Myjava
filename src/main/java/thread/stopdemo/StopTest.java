package thread.stopdemo;

/**
 * Created by anjunli on  2020/12/31
 * 中断线程的方法
 **/
public class StopTest {
    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        testThread.run();

/*        Thread thread = new Thread(new TestThread());
        thread.setDaemon(true);
        thread.run();*/
    }
}
