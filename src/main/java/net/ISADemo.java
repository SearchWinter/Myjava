package net;

import java.net.InetSocketAddress;

/**
 * @Description InetSocketAddress——主要作用是封装端口，在InetAddress基础上加端口，但是它是有构造器的
 *              InetSocketAddress不基于任何协议，一般用于socket编程中。通过“IP地址+端口号”来区分不同的服务。
 * @Date 2020/8/18  15:44
 **/
public class ISADemo {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("113.57.172.19", 8080);
        //不能解析的话，输出ip
        System.out.println(inetSocketAddress.getHostName());
        // ip port
        System.out.println(inetSocketAddress.getAddress());
        System.out.println(inetSocketAddress.getPort());

    }
}
