package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description Executors创建线程池的弊端
 * 1）FixedThreadPool和SingleThreadPool:
 *   允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
 * 2）CachedThreadPool:
 *   允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。
 *
 *
 * @Date 15:44 2021/5/19
 **/
public class TestThreadPool {

//	private static ExecutorService es = new ThreadPoolExecutor(50, 100, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(100000));
	
	private static ExecutorService es = Executors.newFixedThreadPool(50);
//	private static ExecutorService es= Executors.newCachedThreadPool();

	public static void main(String[] args) throws Exception {
		//
		for (int i = 0; i < 10000; i++) {
/*			es.execute(new Runnable() {
				@Override
				public void run() {

				}
			});*/
			es.execute(() -> {
				System.out.print(1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}

		ThreadPoolExecutor tpe = ((ThreadPoolExecutor) es);

		while (true) {
			System.out.println();

			int queueSize = tpe.getQueue().size();
			System.out.println("当前排队线程数：" + queueSize);

			int activeCount = tpe.getActiveCount();
			System.out.println("当前活动线程数：" + activeCount);

			long completedTaskCount = tpe.getCompletedTaskCount();
			System.out.println("执行完成线程数：" + completedTaskCount);

			long taskCount = tpe.getTaskCount();
			System.out.println("总线程数：" + taskCount);

			Thread.sleep(3000);
		}

	}

}
