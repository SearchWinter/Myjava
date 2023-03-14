package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Date 2020/8/24  18:19
 **/
public class ShutDownADemo {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        final long waitTime=8*1000;
        final long awaitTime=5*1000;

        Runnable task1=new Runnable() {
            @Override
            public void run() {
                System.out.println("task1 start");
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable task2=new Runnable() {
            @Override
            public void run() {
                System.out.println("task2 start");
                try {
                    Thread.sleep(awaitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        fixedThreadPool.execute(task1);
        for (int i = 0; i <1000 ; i++) {
            fixedThreadPool.execute(task2);
        }

        fixedThreadPool.shutdown();
        try {
            if(!fixedThreadPool.awaitTermination(awaitTime, TimeUnit.MILLISECONDS)){
                fixedThreadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("awaitTermination interrupted: "+e);
            fixedThreadPool.shutdownNow();
        }
        System.out.println("end");
    }
}
