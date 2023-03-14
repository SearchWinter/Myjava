package timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by anjunli on  2021/5/17
 * java.util.Timer的简单示例
 *
 * 多线程并行处理定时任务时，Timer运行多个TimeTask时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用ScheduledExecutorService则没有这个问题。
 *
 *     //org.apache.commons.lang3.concurrent.BasicThreadFactory
 *     ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
 *         new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
 *     executorService.scheduleAtFixedRate(new Runnable() {
 *         @Override
 *         public void run() {
 *             //do something
 *         }
 *     },initialDelay,period, TimeUnit.HOURS);
 *
 **/
public class TimerTest {
    public static void main(String[] args) {
        Timer timerTest = new Timer("TimerTest");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("timerTask test");
            }
        };
        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("timerTask1 test");
            }
        };
        //任务 首次执行的延迟 时间间隔
        timerTest.schedule(timerTask,1000L,5000L);
        timerTest.schedule(timerTask1,1000L,3000L);
    }
}
