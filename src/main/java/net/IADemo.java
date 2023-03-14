package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Description InetAddress——封装IP及DNS
 * @Date 2020/8/18  16:47
 **/
public class IADemo {
    public static void main(String[] args) throws UnknownHostException {
        //1、使用getLocalHost()创建InetAddress对象
        InetAddress localHost = InetAddress.getLocalHost();
        //获得本机ip地址
        System.out.println(localHost.getHostAddress());
        //获得本机的名称
        System.out.println(localHost.getHostName());

        //2、根据域名得到InetAddress对象
        InetAddress byName = InetAddress.getByName("www.baidu.com");
        System.out.println(byName.getHostAddress());
        System.out.println(byName.getHostName());

        //通过ip得到InetAddress对象
        InetAddress byName1 = InetAddress.getByName("36.152.44.96");
        System.out.println(byName1.getHostAddress());
        //如果ip地址存在，并且能够解析就会输出www.baidu.com；不能解析就输出ip本身
        System.out.println(byName1.getHostName());
    }
}
