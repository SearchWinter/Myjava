package thread.wnotify;


import java.util.LinkedList;

/**
 * @Description ToDO
 **/
public class Storage {
    //仓库容量
    private final int MAX_SIZE=10;
    //仓库的载体
    private LinkedList<Object> list=new LinkedList<>();

    /** 生产方法*/
    public void produce() throws InterruptedException {
        synchronized(list){
            while(list.size()+1>MAX_SIZE){
                System.out.println("生产者"+ Thread.currentThread().getName()+" 仓库已满");
                //使当前线程等待，直到它被唤醒(通知或中断)。
                list.wait();
            }
            list.add(new Object());
            System.out.println("生产者"+ Thread.currentThread().getName()+" 生产一个产品，现库存："+list.size());
            //唤醒在该对象监视器上等待的所有线程
            list.notifyAll();
        }
    }

    /** 消费方法
     *  生产一次，消费一次
     * */
    public void consume() throws InterruptedException {
        synchronized (list){
            while (list.size()==0){
                System.out.println("消费者"+ Thread.currentThread().getName()+"仓库已空");
                list.wait();
            }
            //检索并删除此列表的头(第一个元素)
            list.remove();
            System.out.println("消费者"+ Thread.currentThread().getName()+"消费一个产品，现库存"+list.size());
            list.notifyAll();
        }
    }

    /** 生产十次，消费一次*/
    public void consume2(){
        synchronized(list){
            while(list.size()==MAX_SIZE){
                System.out.println("消费者"+ Thread.currentThread().getName()+"一次消费十个"+list.size());
                list.notifyAll();
                list.clear();
            }
        }
    }
}
