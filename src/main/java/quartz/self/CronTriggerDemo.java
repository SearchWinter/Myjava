package quartz.self;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.quartz.impl.matchers.GroupMatcher.jobGroupEquals;

/**
 * Created by anjunli on  2021/4/26
 * 1、startAt() startNow()分析  对照mysql qrtz_triggers
 * 2、多次启动，注释scheduleJob() 直接start()分析
 **/
public class CronTriggerDemo {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        /** 分开执行*/
//        cron1();
//        resumCron();

        /** 重复注册程序可以继续运行*/
        try {
            cron1();
        } catch (ObjectAlreadyExistsException e) {
            //重复注册会抛出这个异常，捕获然后执行下一个
            System.out.println(e.getMessage());
            resumCron();
        }
    }

    //基本模式
    private static void cron1() throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobKey jobKey = JobKey.jobKey("job2", "group2");
        JobDetail jobDetail = JobBuilder.newJob(PrintDateJob.class).withIdentity(jobKey).build();

        TriggerKey triggerKey = TriggerKey.triggerKey("tri2", "triGroup2");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .startAt(new Date(1619707000000L))
//                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * ? * *").withMisfireHandlingInstructionDoNothing())
                .build();

//        MyJobListener myJobListener = new MyJobListener();
        //添加监听器，对特定组
//        scheduler.getListenerManager().addJobListener(myJobListener,jobGroupEquals("group2"));

        //将任务和触发器关联起来
        scheduler.scheduleJob(jobDetail, cronTrigger);
        System.out.println(new Date());
        scheduler.start();

/*        TimeUnit.MINUTES.sleep(1L);
        scheduler.shutdown(true);*/
    }

    public static void resumCron() throws SchedulerException {
        StdSchedulerFactory sf = new StdSchedulerFactory();
//        Scheduler myScheduler = sf.getScheduler();

        Scheduler myScheduler = StdSchedulerFactory.getDefaultScheduler();
        myScheduler.start();

    }

}
