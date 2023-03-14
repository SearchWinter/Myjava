package io.outputstream;

import java.io.*;

/**
 * @Description 输出流就是从流中写数据到文件中
 * @Date 2020/7/16  10:35
 **/
public class FileOutputStreamTest {
    public static void main(String[] args) throws IOException {
        //使用字符串类型的文件名，来创建输出流对象，文件不存在的，会自动创建
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\test.txt");
        //使用文件对象创建一个输出流对象
        File file = new File("E:\\test.txt");
        FileOutputStream fileOutputStream2 = new FileOutputStream(file);

        //根据输出流对象，创建OutputStreamWriter()
        OutputStreamWriter opsWriter = new OutputStreamWriter(fileOutputStream,"utf-8");

        /** append和write都会将文件之前存在的数据清除*/
        //写入缓冲区，还没有写入文件,从1读取，读到6前面结束 0,1,2....   1 2 3 4 5
        opsWriter.append("rng ig edg tes ",1,6);

        //换行
        opsWriter.write("\r\n");
        //从第二位开始输入，输入5位 0,1,2....
        opsWriter.write("g2 fnc t1",2,5);

        /** append()中的参数可以是null,以字符串"null"的形式添加到文件中，write()会报错*/
        opsWriter.append(null);
//        opsWriter.write(null);

        //返回此流使用的字符编码的名称
        System.out.println(opsWriter.getEncoding());
        //刷新缓存区，写入到文件(因为close的存在，可以省略)
        opsWriter.flush();
        //关闭写入流，同时会把缓冲区的内容写入文件
        opsWriter.close();

    }
}
