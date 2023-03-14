package thread.pool.scheduled;

import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by anjunli on  2021/1/7
 * 1、通过打印出的时间，理解scheduledWithFixedDelay()方法四个参数
 *
 **/
public class Demo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        // 执行线程 初始化延时：对象创建到执行有个延迟时间 两次开始执行的间隔时间 计时单位
        // 以固定的延时执行，第一次执行完成后，延迟1s，再执行第二个任务
        scheduledExecutorService.scheduleWithFixedDelay(new Task(), 10, 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(new LongTask(), 10, 5, TimeUnit.SECONDS);

        // 向JVM添加一个关闭钩子，监听JVM的关闭 做扫尾工作
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run(){
                scheduledExecutorService.shutdownNow();
            }
        });
    }
}
