package quartz.store;

import org.quartz.*;
import org.quartz.CronTrigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by anjunli on  2021/5/6
 * startAt()  可以执行历史任务，从过去时间开始执行
 **/
public class CronTrigger2 {
    public static void main(String[] args) throws SchedulerException {
        StdSchedulerFactory std = new StdSchedulerFactory();
        Scheduler scheduler = std.getScheduler();

        JobKey jobKey = JobKey.jobKey("job1", "group1");
        JobDetail jobDetail = JobBuilder.newJob(MailJob.class).withIdentity(jobKey).build();
        TriggerKey triggerKey = TriggerKey.triggerKey("tri1", "triGroup1");

        if (scheduler.checkExists(jobKey)) {
            scheduler.pauseTrigger(triggerKey);
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
            System.out.println("******1******");
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * ? * *")
                            .withMisfireHandlingInstructionIgnoreMisfires())
                    .startAt(new Date(1620271800000L))
                    .build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } else {
            System.out.println("*******2*******");
        }
        scheduler.start();
    }
}
