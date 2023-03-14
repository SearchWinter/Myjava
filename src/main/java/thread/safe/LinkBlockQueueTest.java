package thread.safe;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description LinkedBlockingQueue常用方法
 * @Date 2020/8/11  15:52
 **/
public class LinkBlockQueueTest {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Integer> lbqueue = new LinkedBlockingQueue<>(1000);
        for (int i = 0; i <100 ; i++) {
            lbqueue.put(i);
        }
        System.out.println(lbqueue);

        System.out.println(lbqueue.take());
    }
}
