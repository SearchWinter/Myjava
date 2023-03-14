package quartz.self;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by anjunli on  2021/4/26
 * Quartz示例本地运行
 * org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore
 **/
public class SimpleTriggerDemo {
    public static void main(String[] args) {
        try {
            // 1、创建调度器Scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
            JobDetail jobDetail = JobBuilder.newJob(PrintDateJob.class)
                    .withIdentity("myjob","group1")
                    .build();
            // 3、构建Trigger实例,每隔2s执行一次，直到程序结束
            SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("myjob", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                    .build();
            // 4、执行
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();

            TimeUnit.MINUTES.sleep(1);
            scheduler.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}