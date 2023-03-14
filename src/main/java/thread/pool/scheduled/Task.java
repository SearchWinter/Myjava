package thread.pool.scheduled;

import scala.tools.nsc.Global;

import java.util.Date;

/**
 * Created by anjunli on  2021/1/7
 **/
public class Task implements Runnable {
    public Task() {
        System.out.println("Task create time : " +new Date());
    }

    @Override
    public void run() {
        try {
            System.out.println("Task execute :"+new Date());
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
