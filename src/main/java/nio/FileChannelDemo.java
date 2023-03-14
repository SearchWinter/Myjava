package nio;


import org.apache.commons.lang.StringUtils;
import org.junit.Test;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Set;

/**
 * @ClassName FileChannelDemo
 * @Description TODO
 * @Author Li Anjun
 * @Date 2020/7/16  15:12
 **/
public class FileChannelDemo {
    /**
     * 配合缓冲区，实现文件复制功能
     * 从test里面读取数据
     */
    @Test
    public void FileCopy() throws IOException {
        //1、获取FileChannel通道
        Path path = Paths.get("data/read.txt");
        FileChannel fileInChannel = FileChannel.open(path, StandardOpenOption.READ);

        Path path1 = Paths.get("E:\\test2.txt");
        FileChannel fileOutChannel = FileChannel.open(path1, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //2、分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //3、将通道中的数据存入缓冲区中
        while (fileInChannel.read(byteBuffer) != -1) {
            //切换读取数据的模式
            byteBuffer.flip();
            //将缓冲区中的数据写入通道中，这一步就已经再往文件里面写数据
            fileOutChannel.write(byteBuffer);
            //如果没有这一句，程序会一直执行，也就是会一直讲byteBuffer里面的数据写入文件
            byteBuffer.clear();
        }

    }
    @Test
    public void testEmpty() throws IOException {
        Path path = Paths.get("data/AT.ATStatServer1_HSProxyHATestServer_report_20210125.log");
        FileChannel open = FileChannel.open(path, StandardOpenOption.READ);
        BufferedReader bufReader = new BufferedReader(Channels.newReader(open, "utf-8"));
        String line = "";
        do {
            //读到最后的时候回返回NULL
            line = bufReader.readLine();
            if (StringUtils.isNotEmpty(line)) {
                System.out.println(line);
            }
        } while (line!=null);
    }

    /**
     * 通过notepad++改变文件的编码格式，尝试读取文件内容
     * latin1 用utf-8读取，java.nio.charset.MalformedInputException: Input length = 1
     */
    @Test
    public void testISO() throws IOException {
        Path path = Paths.get("E:\\iso-8859-1.log");
        Path path2 = Paths.get("E:\\utf-demo.log");
        Path path3 = Paths.get("E:\\项目\\Charset\\ATGSQ.TradeServer_sharedata_20210123.log");
        Path path4=Paths.get("E:\\项目\\日志查询\\CRM.ContractWebServer_setPdf_20210125.log");
        FileChannel open = FileChannel.open(path4, StandardOpenOption.READ);
        String charSetName=getFileCharset(path4);
        System.out.println("charSetName: "+charSetName);
        if (StringUtils.isEmpty(charSetName)){
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(Channels.newReader(open, "utf-8"));

        String line = null;
        try {
            line = bufferedReader.readLine();
            while (StringUtils.isNotEmpty(line)) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        }catch (MalformedInputException e){
            System.out.println("read file fail");
        }
    }

    /** 获取已知编码中的具体编码格式*/
    public static String getFileCharset(Path path) {
        try {
            FileChannel open = FileChannel.open(path, StandardOpenOption.READ);
            BufferedReader bufferedReader = new BufferedReader(Channels.newReader(open, "UTF-8"));
            try {
                bufferedReader.readLine();
                return "UTF-8";
            } catch (MalformedInputException e) {
                bufferedReader = new BufferedReader(Channels.newReader(open, "ISO-8859-1"));
                try {
                    bufferedReader.readLine();
                    return "ISO-8859-1";
                } catch (MalformedInputException exe) {
                    System.out.println("|can not fund charset： " + path);
                }
            } finally {
                try {
                    open.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("open path error： " + path);
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void getCharSet() {
        /** 获得Java支持编码集合*/
        Set<String> strings = Charset.availableCharsets().keySet();
        System.out.println(strings);
        /** 获得系统默认编码*/
        Charset charset = Charset.defaultCharset();
        System.out.println(charset);
    }

    /**
     * 添加position后文件无法读取
     * */
    @Test
    public void testPosition() throws IOException {
        Path path = Paths.get("E:\\项目\\日志查询\\AI.DakaWebServer_cache_20210125.log");
        FileChannel open = FileChannel.open(path, StandardOpenOption.READ);
        FileChannel open2 = FileChannel.open(path, StandardOpenOption.READ);
        String charSetName=getFileCharset(path);
        System.out.println(charSetName);
//        open.position(2274096);
        open.position(2274099);
        BufferedReader bufferedReader = new BufferedReader(Channels.newReader(open, "UTF-8"));
        BufferedReader bufferedReader2 = new BufferedReader(Channels.newReader(open2, "UTF-8"));
        for (int i = 0; i <50 ; i++) {
            System.out.println(bufferedReader.readLine());
            System.out.println(bufferedReader2.readLine());
        }
        open.close();
    }

}
