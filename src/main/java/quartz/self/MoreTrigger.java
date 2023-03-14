package quartz.self;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by anjunli on  2021/4/29
 * 每个任务JobDetail可以绑定多个Trigger，但一个Trigger只能绑定一个任务
 * 运行时，quartz.properties中  org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore
 **/
public class MoreTrigger {
    public static void main(String[] args) throws SchedulerException {
        run1();
    }

    public static void run1() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //表明该任务没有任何trigger绑定时仍保存在Quartz的JobStore中,默认为false
        JobDetail job = JobBuilder.newJob(PrintJob2.class)
                .withIdentity("job1", "group1")
                .storeDurably()
                .build();

        TriggerKey triggerKey1 = TriggerKey.triggerKey("tri1", "triGroup1");
        SimpleTrigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey1)
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(10).withIntervalInSeconds(3))
                .forJob(job)
                .build();

        TriggerKey triggerKey2 = TriggerKey.triggerKey("tri2", "triGroup1");
        SimpleTrigger trigger2 = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey2)
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(5).withIntervalInSeconds(5))
                .forJob(job)
                .build();

        //添加一个任务到Quartz框架中，等待后面再绑定Trigger
        scheduler.addJob(job,true);

        /** 可以加入多个Trigger，也可以使用rescheduleJob替换Trigger*/
        //加入的trigger必须指明JOB
        scheduler.scheduleJob(trigger1);
//        scheduler.scheduleJob(trigger2);

        //替换Trigger,必须要关联相同的JOB才行
        scheduler.rescheduleJob(triggerKey1,trigger2);

        scheduler.start();
    }
}
