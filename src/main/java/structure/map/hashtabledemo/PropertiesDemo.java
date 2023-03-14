package structure.map.hashtabledemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by anjunli on  2022/10/24
 *
 **/
public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        //添加key-value
        properties.setProperty("name", "tom");

        //根据key获取值
        String name = properties.getProperty("name");
        System.out.println(name);

        //将此 Properties 表中的属性列表（键和元素对）写入输出流，没有会自动创建
        String path = "E:\\demo.properties";
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        properties.store(fileOutputStream,"Properties类实例");

        // 从输入流中读取属性列表（键和元素对）
        FileInputStream fileInputStream = new FileInputStream(path);
        Properties properties2 = new Properties();
        properties2.load(fileInputStream);

        String name2 = properties2.getProperty("name");
        System.out.println(name2);
    }
}
