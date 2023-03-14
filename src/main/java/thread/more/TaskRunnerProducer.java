package thread.more;

import java.util.concurrent.BlockingQueue;

/**
 * Created by anjunli on  2020/11/13
 **/
public class TaskRunnerProducer implements Runnable {
    public BlockingQueue queue;

    public TaskRunnerProducer(BlockingQueue blockingQueue) {
        this.queue = blockingQueue;
    }

    @Override
    public void run() {
        while(true) {
            if (queue.poll()==null){
                continue;
            }
            System.out.println("Math.Random()*100:" + queue.poll());
        }
    }
}
