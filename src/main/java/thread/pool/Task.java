package thread.pool;

/**
 * @Description 创建两个线程，并使用sleep()模拟执行时间
 * @Date 2020/8/20  23:32
 **/
 class Task implements Runnable{

    @Override
    public void run() {
/*        try {
            Thread.sleep(1*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(Thread.currentThread().getName()+"|短处理任务");
    }
}

class longTask implements Runnable{
    @Override
    public void run() {
/*        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        System.out.println(Thread.currentThread().getName()+"|长处理任务");
    }
}
