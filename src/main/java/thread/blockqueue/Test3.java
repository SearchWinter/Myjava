package thread.blockqueue;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static thread.blockqueue.StorageBlock2.lbqueue;

/**
 * @Description 修改StorageBlock2中注释，即可查看线程安全、不安全时产生的数据。
 * @Date 2020/8/11  11:33
 **/
public class Test3 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(11000);

        StorageBlock2 storageBlock = new StorageBlock2();

        ArrayList<Producer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i, new Producer(storageBlock, "p" + i));
            executorService.execute(list.get(i));
        }

        executorService.execute(new Consumer(storageBlock,"c1"));
        executorService.execute(new Consumer(storageBlock,"c2"));

        //关闭线程池
        executorService.shutdownNow();
        try {
            executorService.awaitTermination(30, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /** 没有消费者，查看非线程安全的结果*/
/*        System.out.println(queue.size());
        System.out.println("**********");
        System.out.println(queue);
        System.out.println(queue.size());*/

        /** 没有消费者，查看线程安全下，添加数据的结果*/
        System.out.println(lbqueue.size());
        System.out.println("***********");
        System.out.println(lbqueue);
        System.out.println(lbqueue.size());


    }
}
