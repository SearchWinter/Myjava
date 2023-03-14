package thread.create.exThread;

/**
 * @Description 通过继承Thread来创建线程
 * @Date 2020/7/30  11:12
 **/
public class ThreadDemo extends Thread {
    private String threadName;

    ThreadDemo(String name){
        threadName=name;
    }

    public String getThreadName() {
        return threadName;
    }

    @Override
    public void run() {
        System.out.println("Running "+threadName);
    }
}
