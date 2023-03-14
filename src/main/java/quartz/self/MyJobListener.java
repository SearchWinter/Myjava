package quartz.self;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * Created by anjunli on  2021/4/27
 **/
public class MyJobListener implements JobListener{
    @Override
    public String getName() {
        return JobListener.class.getName();
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("执行："+context.getJobDetail().getKey().getName());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("阻塞");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("执行结束："+context.isRecovering());

    }
}
