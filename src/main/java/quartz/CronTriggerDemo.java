package quartz;

import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by anjunli on  2021/5/17
 * Quartz Crontrigger 所有运行时间
 **/
public class CronTriggerDemo {
    public static void main(String[] args) throws ParseException {
        CronTriggerImpl cronTrigger = new CronTriggerImpl();
        cronTrigger.setCronExpression("0/5 * * ? * * ");
        // cron表达式，日历，次数
        List<Date> dates = TriggerUtils.computeFireTimes(cronTrigger, null, 10);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Date date:dates){
            System.out.println(simpleDateFormat.format(date));
        }
    }
}
