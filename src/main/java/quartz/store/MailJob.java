package quartz.store;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anjunli on  2021/4/27
 **/
public class MailJob implements Job {

    public MailJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        String email = jobDetail.getJobDataMap().getString("email");

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String now = simpleDateFormat.format(new Date());
//        System.out.printf("给邮件地址 %s 发出了一封定时邮件, 当前时间是: %s (%s)%n" ,email, now,context.isRecovering());

        Date preTime = context.getTrigger().getPreviousFireTime();
        Date nextFireTime = context.getTrigger().getNextFireTime();

        System.out.printf("上一次调用的时间是: %s,下一次调用时间：%s %n " ,preTime,nextFireTime);
    }
}
