package thread.create.imprun;

/** 通过一个Runnable实例来创建*/
public class TestThread{
    public static void main(String[] args) {
        RunnableDemo R1 = new RunnableDemo("Thread-1");
        /** Thread(Runnable threadOb,String threadName);
         *  threadOb 是一个实现 Runnable 接口的类的实例，并且 threadName 指定新线程的名字。
         * */
        Thread thread = new Thread(R1, R1.getThreadName());

        /** 这个方法必须在start()方法执行之前*/
        //将该进程标记为守护进程或用户进程  daemon守护进程
/*        thread.setDaemon(true);
        System.out.println(thread.isDaemon());*/

        //执行start()方法，线程才会执行run()方法
        thread.start();

        /** Thread一些重要方法*/
        //改变线程名称
/*        thread.setName("changeName");
        System.out.println(thread.getName());*/

        //改变线程的优先级，1-10，优先级越高越大，默认为5
/*        thread.setPriority(10);
        System.out.println(thread.getPriority());*/


        //中断线程
/*        thread.interrupt();
        System.out.println(thread.isInterrupted());*/

        //测试进程是否处于活动状态
//        System.out.println(thread.isAlive());

        //返回当前正在执行的线程对象的引用
         Thread.currentThread();
    }
}
