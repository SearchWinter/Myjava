package thread.ThreadFactoryDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description TODO
 * @Date 2020/8/24  15:23
 **/
public class Demo {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5, new DefaultThreadFactory("demo"));


    }
}
