package executor;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;

import java.io.IOException;

/**
 * Created by anjunli on  2021/6/8
 * commons-exec DefaultExecutor的使用方法
 **/
public class DefaultExecutorDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        DefaultExecutor defaultExecutor = new DefaultExecutor();
        //参数必须是executable
        CommandLine cmd1 = new CommandLine("calc");
        //参数是字符串，第一个元素是executable
        CommandLine cmd2 = CommandLine.parse("ping www.baidu.com");
//        defaultExecutor.execute(cmd2);
        defaultExecutor.execute(cmd1);

        execCmd();
    }

    public static void execCmd() throws IOException, InterruptedException {
        DefaultExecutor executor = new DefaultExecutor();
        CommandLine commandLine = new CommandLine("net");
        commandLine.addArgument("start");
        commandLine.addArgument("telnet");
        //
        executor.setExitValue(1);

        ExecuteWatchdog watchdog = new ExecuteWatchdog(30 * 1000);
        executor.setWatchdog(watchdog);
        DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();
        executor.execute(commandLine, handler);
        handler.waitFor();

    }
}
