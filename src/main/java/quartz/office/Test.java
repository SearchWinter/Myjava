package quartz.office;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.newJob;

/**
 * Created by anjunli on  2021/4/26
 **/
public class Test {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().put("sKey","sValue");

        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myjob", "mygroup")
                .usingJobData("j1", "jv1")
                .usingJobData("demo","demo1")
                .build();
        job.getJobDataMap().put("j2","jv2");


        //程序启动就开始执行，每个5s一次，直到程序结束
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .usingJobData("t1", "tv1")
                .usingJobData("demo","demo2")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();
        simpleTrigger.getJobDataMap().put("t2","tv2");
        System.out.println(simpleTrigger.getStartTime());

        //注册trigger并启动scheduler
        scheduler.scheduleJob(job,simpleTrigger);
        scheduler.start();

        //在调用shutdown()前，要给job留时间
        TimeUnit.MINUTES.sleep(1L);
        scheduler.shutdown();
    }
}
