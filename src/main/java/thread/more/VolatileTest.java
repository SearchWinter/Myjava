package thread.more;

/**
 * Created by anjunli on  2021/5/17
 * 不加关键字，while (!flag)进行判断的flag 是在线程工作内存当中获取，而不是从 “主内存”中获取。程序会一直执行
 **/
public class VolatileTest extends Thread {
     volatile boolean flag = false;
//     boolean flag = false;
    int i = 0;

    @Override
    public void run(){
        while(!flag){
            i++;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        volatileTest.start();
        Thread.sleep(2000L);
        volatileTest.flag=true;
        System.out.println(volatileTest.i);
    }
}
