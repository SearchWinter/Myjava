package thread.wnotify;

/**
 * @Description ToDO
 **/
public class Test {
    public static void main(String[] args) {
        Storage storage = new Storage();

        //三个生产者
        Thread p1 = new Thread(new Producer(storage, "p1"));
        Thread p2 = new Thread(new Producer(storage, "p2"));
        Thread p3 = new Thread(new Producer(storage, "p3"));

        //三个消费者
        Thread c1 = new Thread(new Consumer(storage, "c1"));
//        Thread c2 = new Thread(new Consumer(storage, "c2"));
//        Thread c3 = new Thread(new Consumer(storage, "c3"));


        p1.start();
        p2.start();
        p3.start();

        c1.start();
//        c2.start();
//        c3.start();
    }

}
