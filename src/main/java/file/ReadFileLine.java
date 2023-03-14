package file;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anjunli on  2020/12/25
 * \r \n
 **/
public class ReadFileLine {
    /** 只要执行了readLine(),读取位置就向前移动了，注意避免覆盖的问题
     * */
    @Test
    public void testReader() throws IOException {
        Path path = Paths.get("data/read.txt");
        FileChannel open = FileChannel.open(path, StandardOpenOption.READ);
        BufferedReader bufferedReader = new BufferedReader(Channels.newReader(open, "utf-8"));
        String line = bufferedReader.readLine();
        List<String> lines=new ArrayList<>();

        while(!strIsEmpty(line)){
            lines.add(line);
            line=bufferedReader.readLine();
        }
        System.out.println(lines);
    }

    /** 和上一个方法对比，这样读取就会少了第一行，同时添加了null(最后一个)*/
    @Test
    public void testReader12() throws IOException {
        Path path = Paths.get("data/read.txt");
        FileChannel open = FileChannel.open(path, StandardOpenOption.READ);
        BufferedReader bufferedReader = new BufferedReader(Channels.newReader(open, "utf-8"));
        String line = bufferedReader.readLine();
        List<String> lines=new ArrayList<>();
        while(!strIsEmpty(line)){
            line=bufferedReader.readLine();
            lines.add(line);
        }
        System.out.println(lines);
    }
    @Test
    public void testReader2() throws IOException {
        int i=0;
        int skipN=0;
        while(i<5) {
            i=i+1;
            Path path = Paths.get("data/read.txt");
            FileChannel open = FileChannel.open(path, StandardOpenOption.READ);
            BufferedReader bufferedReader = new BufferedReader(Channels.newReader(open, "utf-8"));

            bufferedReader.skip(0);
        }
    }

    private boolean strIsEmpty(String line) {
        if(line==null) {
            return true;
        }
        return line.trim().isEmpty();
    }

    // \r回车 \n换行
    @Test
    public void testA(){
        System.out.println("换行 \r\n is for windows");
    }


    @Test
    public void testB(){
        //回到本行行首，会将这一行以前的内容覆盖掉
        System.out.println("换行 \r is for mac");
    }
    @Test
    public void testC(){
        System.out.println("换行 \n is for linux");
    }
}
