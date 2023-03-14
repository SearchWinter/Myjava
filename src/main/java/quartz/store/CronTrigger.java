package quartz.store;

import org.quartz.*;
import org.quartz.impl.DirectSchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by anjunli on  2021/4/27
 * 获取指定的Schedule
 **/
public class CronTrigger {
    public static void main(String[] args) throws InterruptedException, SchedulerException {
        try{
            assginNewJob();
        }catch (Exception e){
            System.out.println("从数据库中读取任务！！！");
            e.printStackTrace();
            resumeDb();
        }
    }

    private static void assginNewJob() throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobKey jobKey=JobKey.jobKey("job1","jobGroup1");
        JobDetail jobDetail = JobBuilder.newJob(MailJob.class)
                .withIdentity(jobKey)
                .usingJobData("email", "11111111111")
                .build();

        TriggerKey triggerKey = TriggerKey.triggerKey("tri1", "triGroup1");
        org.quartz.CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * ? * *"))
                .startNow()
                .forJob(jobKey)
                .build();


        scheduler.scheduleJob(jobDetail,cronTrigger);

        scheduler.start();
        TimeUnit.SECONDS.sleep(6);
        scheduler.shutdown(true);

    }

    private static void resumeDb() throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        StdSchedulerFactory stdf = new StdSchedulerFactory();
        Scheduler myScheduler = stdf.getScheduler("MyScheduler");

        myScheduler.start();

        TimeUnit.MINUTES.sleep(1L);
        myScheduler.shutdown(true);
    }
}
