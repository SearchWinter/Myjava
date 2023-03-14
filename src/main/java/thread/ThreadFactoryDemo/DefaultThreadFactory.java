package thread.ThreadFactoryDemo;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 线程工厂、
 * @Date 2020/8/13  15:11
 **/
 class DefaultThreadFactory implements ThreadFactory {
    private final ThreadGroup group;
    //原子类保证数据一致性
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    DefaultThreadFactory(String prefix) {
        //获取系统安全接口。
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = "TafLogReader-" + prefix + "-";
    }

    @Override
    public Thread newThread(Runnable r) {
        //分配一个新的线程对象，使其以r为运行对象，以指定的名称为名称，并属于按组引用的线程组,并具有指定的堆栈大小
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        //提供公共服务，保证所有用户针对该线程的请求都能有响应。
        if (!t.isDaemon())
            t.setDaemon(true);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
