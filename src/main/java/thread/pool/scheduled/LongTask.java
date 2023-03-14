package thread.pool.scheduled;

import scala.tools.nsc.Global;

import java.util.Date;

/**
 * Created by anjunli on  2021/1/7
 **/
public class LongTask implements Runnable {

    public LongTask() {
        System.out.println("LongTash create time: "+new Date());
    }

    @Override
    public void run() {
        try {
            System.out.println("long task execute: "+new Date());
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
