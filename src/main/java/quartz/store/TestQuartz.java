package quartz.store;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by anjunli on  2021/4/27
 * SimpleTrigger信息存JDBC示例
 **/
public class TestQuartz {
    public static void main(String[] args) throws Exception {
        System.out.println(new Date());
        try {
            assginNewJob();
        }catch (ObjectAlreadyExistsException  e){
            System.out.println("从数据库里面读取！！！");
            resumeJobFromDataBase();
        }
    }

    private static void resumeJobFromDataBase() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        TimeUnit.MINUTES.sleep(2L);
        scheduler.shutdown(true);
    }

    private static void assginNewJob() throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //总共执行11次，第一次不计数
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(10))
                .build();

        JobDetail jobDetail = JobBuilder.newJob(MailJob.class).withIdentity("mailjob1", "mailgroup")
                .usingJobData("email", "123456789@163.com")
                .build();

        scheduler.scheduleJob(jobDetail,trigger);

        scheduler.start();

        TimeUnit.SECONDS.sleep(15L);
        scheduler.shutdown(true);
    }
}
