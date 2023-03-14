package thread.create.imprun;


/**
 * @Description 通过实现Runnable接口来创建线程
 * @Date 2020/7/30  10:19
 **/
public class RunnableDemo implements Runnable {

    private String threadName;

    RunnableDemo(String name){
        threadName=name;
    }

    public String getThreadName() {
        return threadName;
    }

    /** run() 可以调用其他方法，使用其他类，并声明变量，就像主线程一样。*/
    @Override
    public void run() {
        System.out.println("Running "+threadName);
        String demo="run()声明变量";
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
