package thread.more;


import java.util.ArrayList;

/**
 * @Description 使用volatile关键字来实现线程间的通信——共享内存
 * 同一时刻只能由一个线程来修改notice的值.
 * @Date 2020/7/30  14:39
 **/
public class UseVolatile {
    //定义一个共享变量来实现通信，要使用volatile修饰，不然线程不能及时感知
    static volatile boolean notice = false;
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        //线程A
        Thread threadA = new Thread(() -> {
                for(int i=1;i<=5;i++){
                    list.add("adc");
                    System.out.println("线程A向list中添加一个元素，此时list元素个数为： " + list.size());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        });

        //再启动线程A
        threadA.start();
    }
}
