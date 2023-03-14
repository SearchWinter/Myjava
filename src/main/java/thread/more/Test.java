package thread.more;

import com.google.inject.internal.cglib.core.$LocalVariablesSorter;

import java.util.concurrent.*;

import static thread.blockqueue.StorageBlock2.queue;

/**
 * Created by anjunli on  2020/11/13
 * 使用一个线程生产，多个线程消费
 **/
public class Test {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduleExecutorService = Executors.newScheduledThreadPool(1);
        BlockingQueue queue = new ArrayBlockingQueue<>(10, true);

        TafLogReaderMain tafLogReaderMain = new TafLogReaderMain(queue);
        scheduleExecutorService.scheduleWithFixedDelay(tafLogReaderMain, 5, 1, TimeUnit.SECONDS);

        for (int i = 0; i < 30; i++) {
            TaskRunnerProducer taskRunnerProducer = new TaskRunnerProducer(queue);
            Thread t = new Thread(taskRunnerProducer);
            t.setDaemon(false);
            t.start();
        }
    }
}
