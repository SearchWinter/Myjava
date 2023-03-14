package quartz.self;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by anjunli on  2021/4/29
 *
 **/
public class Test {
    public static void main(String[] args) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey("test1", "testGroup1");
        JobDetail jobDetail = JobBuilder.newJob(PrintJob2.class)
                .withIdentity(jobKey)
                .storeDurably()
                .build();
        try {
            run1();
        } catch (ObjectAlreadyExistsException e) {
            System.out.println(e.getMessage());
            run2();
        }
    }

    public static void run1() throws SchedulerException {
        System.out.println("********1********");
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobKey jobKey = JobKey.jobKey("test1", "testGroup1");
        JobDetail jobDetail = JobBuilder.newJob(PrintJob2.class)
                .withIdentity(jobKey)
                .storeDurably()
                .build();

        TriggerKey triggerKey = TriggerKey.triggerKey("testTri1", "TestTriGroup1");
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .forJob(jobDetail)
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(10).repeatForever())
                .build();

        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
    }

    public static void run2() throws SchedulerException {
        System.out.println("*******2********");
//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        StdSchedulerFactory sf = new StdSchedulerFactory();
        Scheduler myScheduler2 = sf.getScheduler("MyScheduler2");

        TriggerKey triggerKey = TriggerKey.triggerKey("testTri2", "TestTriGroup2");
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(5))
                .forJob("test1","testGroup1")
                .startNow()
                .build();

//        myScheduler2.deleteJob(JobKey.jobKey("job1","group1"));
        myScheduler2.rescheduleJob(TriggerKey.triggerKey("testTri1","TestTriGroup1"),trigger);
        myScheduler2.start();

    }
}
