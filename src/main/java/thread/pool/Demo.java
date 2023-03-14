package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description 模拟内存打满的情况
 * @Date 2020/8/25  11:48
 **/
public class Demo {
    private static ExecutorService executorService= Executors.newFixedThreadPool(2);
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
