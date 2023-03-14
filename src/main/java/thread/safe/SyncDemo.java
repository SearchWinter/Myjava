package thread.safe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description 同步代码块，实现线程安全
 * @Date 2020/8/11  18:56
 **/
public class SyncDemo {
    public static int tickets=20;

    static class SellTickets implements Runnable{
        String name=null;
        public SellTickets() {
        }

        public SellTickets(String name) {
            this.name = name;
        }

        public SellTickets(int tickets, String p1) {
        }

        @Override
        public void run() {
            //同步代码块
            while (tickets>0){
                synchronized(this){
                    if (tickets<=0){
                        System.out.println("售票结束");
                        return;
                    }
                    System.out.println(Thread.currentThread().getName()+"--->售出第"+tickets+"号票");
                    tickets--;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);

        newFixedThreadPool.execute(new SellTickets(tickets,"p1"));

        newFixedThreadPool.shutdownNow();
    }

}
