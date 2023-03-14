package thread.blockqueue;


import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 数据存储载体 生产和消费方法
 **/
public class StorageBlock2 {
    // 仓库存储的载体  线程安全
    public static final int MAX_SIZE = 10000;
    public static BlockingQueue<Integer> lbqueue = new LinkedBlockingDeque<>(MAX_SIZE);
    //非线程安全
    public static Queue<Integer> queue = new ArrayDeque<>(10);

    //整形原子类，多个线程操作同一数据保持顺序；前一线程还未结束，后一线程看到的数据可能是还未更改的
    private AtomicInteger start = new AtomicInteger(0);

    public void produce() {
        //获取当前值，并自增；使用 i++,则可能会出错。两个线程执行1+1，第二个线程读到的数据可能是之前的，最后结果应该为3，出错后为2.
        int i = start.getAndIncrement();
//        queue.add(i);
        try {
            //当队列装满，生产者线程被阻塞；
            lbqueue.put(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consume() {
        //返回并移除队列的第一个元素
/*        if (!queue.isEmpty()) {
            Integer poll = queue.poll();
            System.out.println(poll);
        }*/
            try {
                //当队列为空，消费者线程被阻塞
                lbqueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }
}
