package thread.stopdemo;

/**
 * Created by anjunli on  2020/12/31
 **/
public class TestThread implements Runnable {
    public TestThread() {
    }

    @Override
    public void run() {
        /** while(true) 会一直执行
         * 比如一些监控进程，就需要一直执行
         * */
        while (true){
            System.out.println("thread test");
        }

/*        while(!Thread.interrupted()){
            try {
                Thread.sleep(3000L);
                //中断当前线程
                Thread.currentThread().interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
                //捕捉异常后，执行break跳出循环
                break;
            }
        }*/
    }
}
