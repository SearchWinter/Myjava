package thread.safe;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description TODO
 * @Date 2020/8/12  10:23
 **/
public class LinkedBlockingDequeTest {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<Integer> deque = new LinkedBlockingDeque<>(10);
        AtomicInteger data = new AtomicInteger(10);

        for (int i = 0; i <10 ; i++) {
            int num = data.getAndIncrement();
            deque.put(num);
        }
        System.out.println(deque);

        //add() 如果队列已满，抛出异常
        //在队列头部添加数据
//        deque.add(10);
//        deque.addFirst(100);
        //在队里尾部添加数据
//        deque.addLast(200);

        //没有空间添加数据，调用此方法的线程会被阻塞，直到有空间再继续
//        deque.put(100);

        //清空数据
        deque.clear();

        //移除头部的数据
//        deque.remove();
//        deque.removeFirst();
        //移除尾部的数据
//        deque.removeLast();

        //阻塞，直到有数据后再继续
        deque.take();
        System.out.println(deque);
    }
}
