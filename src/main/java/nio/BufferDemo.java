package nio;


import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @ClassName BufferDemo
 * @Description buffer代码演示，如何建立缓冲区，核心变量的值是如何变化的  clear() rewind()  flip()
 * @Author Li Anjun
 * @Date 2020/7/14  19:00
 **/
public class BufferDemo {
    public static void main(String[] args) {
        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        System.out.println("*********初始值*********");
        show(byteBuffer);


        //添加一些数据到缓冲区
        String s="java java ";
        byteBuffer.put(s.getBytes());

        System.out.println("*********添加数据后*********");
        show(byteBuffer);

        System.out.println("*********执行flip后,limit=position,而position回到初始位置 0；*********");
        //切换成读模式,才能够读取数据
        byteBuffer.flip();
        //执行flip()方法后核心变量的值
        show(byteBuffer);

        //创建一个limit大小的字节数组（只有limit这么多的数据可以读）
        byte[] bytes = new byte[byteBuffer.limit()];
        //从缓冲区读取数据，放到bytes里面
        byteBuffer.get(bytes);
        System.out.println(new String(bytes,0,bytes.length));


        System.out.println("*********rewind(),而position回到初始位置 0；*********");
        //因为上面limit=9,这里再添加容量会不够，所以要重新设置limit的值
        byteBuffer.limit(100);
        String s1="sfsfs";
        byteBuffer.put(s1.getBytes());
        //14=9+5  get()9个字节+put()5个字节
        System.out.println(byteBuffer.position());
        byteBuffer.rewind();
        show(byteBuffer);

        System.out.println("*********执行clear方法后,缓冲区回到初始状态*********");
        byteBuffer.clear();
        show(byteBuffer);


    }
    /** 输出buffer的四个变量值*/
    public static void show(ByteBuffer byteBuffer){
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.mark());
    }
}
