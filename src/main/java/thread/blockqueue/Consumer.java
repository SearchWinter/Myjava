package thread.blockqueue;


/**
 * @Description 消费者
 **/
public class Consumer implements Runnable {
    private StorageBlock2 storageBlock;

    public Consumer() {
    }

    public Consumer(StorageBlock2 storageBlock, String name) {
        this.storageBlock = storageBlock;
    }

    @Override
    public void run() {
        storageBlock.consume();
    }
}
