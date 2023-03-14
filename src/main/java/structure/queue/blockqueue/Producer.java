package structure.queue.blockqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by anjunli on  2021/1/15
 **/
public class Producer implements Runnable {
    public BlockingQueue<Integer> queue;
    public Producer() {
    }

    @Override
    public void run() {
        try {
            queue.put((int) Math.round(Math.random()*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
