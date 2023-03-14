package quartz.self;

import org.quartz.*;
import org.quartz.CronTrigger;
import org.quartz.impl.StdSchedulerFactory;
import quartz.store.MailJob;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by anjunli on  2021/4/28
 * 使用数据库存储Schedule的信息,Schedule相关方法测试
 **/
public class MoreTrigger2 {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobKey jobKey = JobKey.jobKey("job1", "group1");
        JobDetail jobDetail = JobBuilder.newJob(MailJob.class)
                .withIdentity(jobKey)
                .usingJobData("email", "passwd")
                .storeDurably()
                .build();

        TriggerKey triggerKey1 = TriggerKey.triggerKey("tri1", "triGroup1");
        CronTrigger cron1 = TriggerBuilder.newTrigger().withIdentity(triggerKey1)
                .startAt(new Date(1619607000000L))
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * ? * *"))
                .forJob(jobDetail)
                .build();

        TriggerKey triggerKey2 = TriggerKey.triggerKey("tri2", "triGroup1");
        CronTrigger cron2 = TriggerBuilder.newTrigger().withIdentity(triggerKey2)
                .startAt(new Date(1619607000000L))
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * ? * *"))
                .forJob(jobDetail)
                .build();

        if(scheduler.getJobDetail(jobKey)==null){
            System.out.println("*********1********");

            scheduler.addJob(jobDetail,true);
            /** 设置多个trigger*/
            scheduler.scheduleJob(cron1);
//            scheduler.scheduleJob(cron2);
            scheduler.start();
        }else{
            System.out.println("*******2******");
            /** 替换指定JOB的Trigger*/
            scheduler.rescheduleJob(triggerKey1,cron2);

            /** 停止触发器*/
//            scheduler.pauseTrigger(triggerKey1);

            /** 删除触发器*/
//            scheduler.unscheduleJob(triggerKey1);

            /** 从Schedule中删除指定的JOB,与之相关的Trigger也会被删除*/
//            scheduler.deleteJob(jobKey);

            /** 暂停任务*/
//            scheduler.pauseJob(jobKey);
            scheduler.start();
        }

    }
}
