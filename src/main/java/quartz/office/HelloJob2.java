package quartz.office;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by anjunli on  2021/4/26
 * 实现Job接口
 **/
public class HelloJob2 implements Job {

    public HelloJob2() {
    }

    @Override
    public void execute(JobExecutionContext context){
        //todo
        System.out.println("HelloJob2 execute...");
    }
}
