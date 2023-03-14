package nio;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.charset.MalformedInputException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by anjunli on  2021/1/27
 **/
public class CharSetDemo {
    public static void main(String[] args) throws IOException {
        Path path=Paths.get("/usr/local/app/taf/remote_app_log/CRM/ContractWebServer/CRM.ContractWebServer_setPdf_20210125.log");
        Path path2=Paths.get("E:\\项目\\日志查询\\CRM.ContractWebServer_setPdf_20210125.log");
        FileChannel open = FileChannel.open(path2, StandardOpenOption.READ);
        String charSetName=getFileCharset(path2);
        System.out.println("charSetName: "+charSetName);
        if (charSetName.isEmpty()){
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(Channels.newReader(open, charSetName));

        String line = null;
        try {
            line = bufferedReader.readLine();
            while (!line.isEmpty()) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        }catch (MalformedInputException e){
            System.out.println("read file fail");
        }
        open.close();
    }
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
}
