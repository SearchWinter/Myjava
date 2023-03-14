package thread.more;

import java.util.concurrent.BlockingQueue;

/**
 * Created by anjunli on  2020/11/13
 **/
public class TafLogReaderMain implements Runnable {
    private BlockingQueue queue;

    public TafLogReaderMain(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        long round = Math.round(Math.random() * 100);
        System.out.println("put num: "+round);
        try {
            queue.put(round);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
