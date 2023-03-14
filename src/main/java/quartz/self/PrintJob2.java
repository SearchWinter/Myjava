package quartz.self;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by anjunli on  2021/4/29
 **/
public class PrintJob2 implements Job {
    public PrintJob2() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("JobDetail: "+context.getJobDetail().getKey().toString()
                +" Trigger: "+context.getTrigger().getKey().toString()
                +" 上一次执行："+context.getTrigger().getPreviousFireTime()
                +" 下一次执行时间："+context.getTrigger().getNextFireTime());
    }
}
