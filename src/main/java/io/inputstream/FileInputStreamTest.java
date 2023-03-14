package io.inputstream;

import org.junit.Test;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Description 文件输入流案例,读取文件的内容，然后在控制台输出
 * @Date 2020/7/16  10:30
 **/
public class FileInputStreamTest {
    public static void main(String[] args) throws IOException {
        //使用字符串类型的文件名，来创建输入流对象读取文件
            FileInputStream fileInputStream = new FileInputStream("E:\\test.txt");

        //使用文件对象创建一个输入流对象
        File file = new File("E:\\test.txt");
        FileInputStream fileInputStream2 = new FileInputStream(file);

        //read()    int     从这个输入流中读取一个字节的数据
        char read = (char)fileInputStream.read();
        System.out.println(read);

        //构建InputStreamReader对象
        InputStreamReader ipsReader = new InputStreamReader(fileInputStream, "utf-8");

        StringBuffer stringBuffer = new StringBuffer();
        //ready()  boolean     告知该流是否已准备好读取。
        //read()    char        读取单个字符
        while (ipsReader.ready()){
            //转换为char加到StringBuffer里面
            stringBuffer.append((char)ipsReader.read());
        }
        System.out.println(stringBuffer.toString());

        //关闭读取流
        ipsReader.close();

    }
    /** 文本中有中文，怎么读取*/
    @Test
    public void test() throws IOException {
        Path path = Paths.get("E:\\HQPeek.PeekForexServer_debug_20200813.log");
        Path path2 = Paths.get("data/UTF_8.log");
        FileChannel open = FileChannel.open(path2, StandardOpenOption.READ);

        BufferedReader bufReader = new BufferedReader(Channels.newReader(open, "UTF-8"));

        bufReader.lines().forEach(line->{
            String[] split = line.split("\\|", -1);
            for (String str:split) {
                System.out.println(str);
            }
        });

    }

    /** skip()  跳过输入流中n个字节的数据
     *  read() 下一个字节数据的ASCII码 int,如果到了末尾，返回 -1
     * */
    @Test
    public void skipTest() throws IOException, InterruptedException {
        int i=0;
        FileInputStream fileInputStream = new FileInputStream("data/input.txt");
        BufferedInputStream buffer = new BufferedInputStream(fileInputStream);
        while((i=buffer.read())!=-1){
            System.out.println(i);
            char c=(char)i;
            System.out.println(c);
            Thread.sleep(2000);
            buffer.skip(3);
        }

    }
}