package quartz.office;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import java.time.LocalDateTime;

/**
 * Created by anjunli on  2021/4/26
 * 实现Job接口
 * JobExecutionContext中包含了Quartz运行时的环境以及Job本身的详细数据信息。
 * 当Schedule调度执行一个Job的时候，就会将JobExecutionContext传递给该Job的execute()中，Job就可以通过JobExecutionContext对象获取信息。
 **/
public class HelloJob implements Job {

    public HelloJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Object tv1 = context.getTrigger().getJobDataMap().get("t1");
        Object tv2 = context.getTrigger().getJobDataMap().get("t2");
        Object jv1 = context.getJobDetail().getJobDataMap().get("j1");
        Object jv2 = context.getJobDetail().getJobDataMap().get("j2");
        Object sv=null;

        try{
            sv = context.getScheduler().getContext().get("sKey");
        }catch (SchedulerException e){
            e.printStackTrace();
        }
        System.out.println(tv1+":"+tv2);
        System.out.println(jv1+":"+jv2);
        System.out.println(sv);
        System.out.println("hello:"+ LocalDateTime.now());

        //两个JobDataMap的并集，Trigger会覆盖JobDetail的数据
        System.out.println(context.getMergedJobDataMap().get("demo"));
    }
}
