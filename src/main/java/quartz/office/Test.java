package quartz.office;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.newJob;

/**
 * Created by anjunli on  2021/4/26
 * 1、利用JobExecutionContext传递数据
 * 2、Scheduler配置多个job
 **/
public class Test {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().put("sKey", "sValue");

        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myjob", "mygroup")
                .usingJobData("j1", "jv1")
                .usingJobData("demo", "demo1")
                .build();
        job.getJobDataMap().put("j2", "jv2");


        //程序启动就开始执行，每个5s一次，直到程序结束
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .usingJobData("t1", "tv1")
                .usingJobData("demo", "demo2")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();
        simpleTrigger.getJobDataMap().put("t2", "tv2");
        System.out.println(simpleTrigger.getStartTime());

        //注册trigger并启动scheduler
        scheduler.scheduleJob(job, simpleTrigger);
        scheduler.start();

        //创建第二个Job，使用CronScheduleBuilder，可以在Scheduler启动后再添加
        JobDetail job2 = JobBuilder.newJob(HelloJob2.class)
                .withIdentity("myjob2", "mygroup")
                .build();

        CronTrigger trigger2 = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? ").withMisfireHandlingInstructionIgnoreMisfires())
                .build();
        scheduler.scheduleJob(job2, trigger2);


        //在调用shutdown()前，要给job留时间，不执行shutdown会安装trigger里面的逻辑一直执行
//        TimeUnit.MINUTES.sleep(1L);
//        scheduler.shutdown();
    }
}
