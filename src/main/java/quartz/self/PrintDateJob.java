package quartz.self;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by anjunli on  2021/4/26
 **/
public class PrintDateJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //模拟程序执行时间
/*        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date previousFireTime = context.getTrigger().getPreviousFireTime();
        Date nextFireTime = context.getTrigger().getNextFireTime();
        System.out.printf("上一次执行时间：%s,下一次执行时间：%s %n",previousFireTime,nextFireTime);
    }
}
