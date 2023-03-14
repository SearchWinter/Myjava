package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * newCachedThreadPool 创建一个可缓存线程池(Integer.MAX_VALUE);当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
 * @Date 2020/8/20  23:36
 **/
public class NewCachedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        System.out.println("******newCachedThreadPool*******");
        for (int i = 0; i <4 ; i++) {
            Task tPoolDemo = new Task();
            service.execute(tPoolDemo);
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);
    }
}
