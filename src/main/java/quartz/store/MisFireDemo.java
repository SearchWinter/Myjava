package quartz.store;

import org.quartz.*;
import org.quartz.CronTrigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by anjunli on  2021/5/6
 * CronScheduleBuilder  MisFire策略
 * 只有第一种，startAt()设置为过去时间才会生效。
 * withMisfireHandlingInstructionIgnoreMisfires     从错过的第一个时间开始执行，执行所有错过的任务，大于当前时间后，按照正常的cron执行
 * withMisfireHandlingInstructionDoNothing          不触发立即执行，等到下一次cron到当前时间，按照正常cron执行
 * withMisfireHandlingInstructionFireAndProceed     以当前时间触发立即执行，然后按照Cron频率依次执行  默认
 **/
public class MisFireDemo {
    public static void main(String[] args) throws SchedulerException {
        StdSchedulerFactory std = new StdSchedulerFactory();
        Scheduler scheduler = std.getScheduler();

        JobKey jobKey = JobKey.jobKey("job2", "group2");
        TriggerKey triggerKey = TriggerKey.triggerKey("tri2", "tri2Group2");

        JobDetail jobDetail = JobBuilder.newJob(MailJob.class).withIdentity(jobKey).storeDurably().build();
        CronTrigger cronTri = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/5 * * ? * * ")
                                //..withMisfireHandlingInstructionFireAndProceed()
                                //.withMisfireHandlingInstructionDoNothing()
                                .withMisfireHandlingInstructionIgnoreMisfires()
                )
                .startAt(new Date(1620286800000L))
                .build();
        if (scheduler.checkExists(jobKey)) {
            scheduler.pauseTrigger(triggerKey);
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
            System.out.println("**********1**********");
        } else {
            System.out.println("**********2**********");
        }
        scheduler.scheduleJob(jobDetail, cronTri);
        scheduler.start();
    }
}
