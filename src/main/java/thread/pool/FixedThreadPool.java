package thread.pool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description newFixedThreadPool  创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
 * @Date 2020/8/21  15:43
 **/
public class FixedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        fixedThreadPool.execute(new Task());
        fixedThreadPool.execute(new Task());
        fixedThreadPool.execute(new longTask());

        // 阻塞队列
        for (int i = 0; i <100 ; i++) {
            fixedThreadPool.execute(new Task());
        }


//        fixedThreadPool.shutdown();
//        fixedThreadPool.awaitTermination(2,TimeUnit.SECONDS);

        //返回未执行的任务列表
        List<Runnable> runnables = fixedThreadPool.shutdownNow();
        System.out.println(runnables.size());
//        System.out.println(runnables);

        //返回Boolean值    每隔1秒钟监测一次线程池的关闭情况,判断线程池中是否还有继续运行的线程
        while(!fixedThreadPool.awaitTermination(2, TimeUnit.SECONDS)){
            System.out.println("线程池没有关闭");
        }
        System.out.println("线程池已经关闭");
    }
}
