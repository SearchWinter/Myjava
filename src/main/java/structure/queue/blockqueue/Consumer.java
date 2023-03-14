package structure.queue.blockqueue;

import scala.tools.nsc.Global;

import java.util.concurrent.BlockingQueue;

/**
 * Created by anjunli on  2021/1/15
 **/
public class Consumer implements Runnable {
    public BlockingQueue<Integer> queue;
    public Consumer() {
    }

    @Override
    public void run() {

    }
}
