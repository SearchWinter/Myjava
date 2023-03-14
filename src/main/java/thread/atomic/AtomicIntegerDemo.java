package thread.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 整型原子类
 * @Date 2020/8/11  11:08
 **/
public class AtomicIntegerDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count = new AtomicInteger(10);
        System.out.println(count.get());
    }
}
