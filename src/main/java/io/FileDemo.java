package io;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;

/**
 * @Description java.io.File各种方法的示例
 * 有的方法是可以直接执行的，示例中得到输出值并在控制台输出只是为了验证方法有没有执行成功
 * File     文件和目录路径名的抽象表示。
 * @Date 2020/7/17  9:45
 **/
public class FileDemo {
    public static void main(String[] args) throws Exception {
        //构造方法,通过给定绝对路径
        File file = new File("E:\\test.txt");
        //通过URI
        File file1 = new File(new URI("file:///E:/test.txt"));

        //existe()  boolean     测试该绝对路径指示的文件是否存在
/*        boolean exists = file.exists();
        System.out.println(exists);*/

        //createTempFile()      file        通过给定的前缀和后缀，在指定目录下创建临时文件；不指定就是在默认目录下创建
/*        File tempfile = new File("E:\\Temp");
        File.createTempFile("temp",".txt",tempfile);*/

        //length()  int  返回这个file对象表示的文件的长度
//        System.out.println(file.length());

        //createNewFile()   boolean     当这个文件不存在的时候，创建一个新文件，true表示创建成功
/*
        File file2 = new File("E:\\test3.txt");
        boolean b = file2.createNewFile();
        System.out.println(b);
        //delete()      boolean        删除文件
        boolean delete = file2.delete();
        System.out.println(delete);
*/
        //getAbsoluteFile       File        返回此抽象路径名的绝对形式。
        File absoluteFile = file.getAbsoluteFile();
        //getAbsolutePath       String      返回该文件的绝对路径
        String absolutePath = file.getAbsolutePath();

        //getPath       String      将抽象路径名转换为路径名字符串。
//        System.out.println(file.getPath());

        //getName()     String      返回由这个抽象路径名表示的文件或目录的名称。
//        System.out.println(file.getName());

        //getParent()      String       返回抽象路径名父目录的路径名字符串，如果该路径名没有命名父目录，则返回null。
//        System.out.println(file.getParent());

        //hashCode()    int     计算此抽象路径名的哈希码。
//        System.out.println(file.hashCode());

        //lastModified      long        返回由这个抽象路径名表示的文件最后被修改的时间
//        System.out.println(file.lastModified());

        //toPath()      Path    返回一个java.nio.file.Path
//        Path path = file.toPath();

        //toURI()        URI    返回一个URI
        URI uri = file.toURI();

        //listFiles()   返回包含目录中所有文件的路径名的数组
        File file2 = new File("E:\\img");
        File[] files = file2.listFiles();
        //三级目录，获取里面所有文件的路径
        for (File f : files) {
            System.out.println(f);

            File[] files1 = f.listFiles();
            for(File f2:files1){
                System.out.println(f2);

                File[] files2 = f2.listFiles();
                for (File f3:files2){
                    System.out.println(f3);
                }
            }
        }

    }
}
