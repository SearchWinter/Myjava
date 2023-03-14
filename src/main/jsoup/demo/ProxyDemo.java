package demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @Description jsoup 设置http代理IP爬取数据
 * @Date 2020/8/18  17:59
 **/
public class ProxyDemo {
    public static void main(String[] args) throws IOException {
        String url="http://importnews.upchinaproduct.com/importNews";
        String proxyHost="221.10.217.54";
        int proxyPort=4285;

        // 创建代理服务器  ISA封装端口
        InetSocketAddress addr = new InetSocketAddress(proxyHost, proxyPort);
        //http 代理
        Proxy.Type proxyType = Proxy.Type.HTTP;
        Proxy proxy = new Proxy(proxyType, addr);

        Document document = Jsoup.connect(url).proxy(proxy)
                .ignoreContentType(true)
                .get();
        System.out.println(document.body().text());
    }

    /** 数据中心，table*/
    @Test
    public void tableDemo() throws IOException {
        String url ="https://www.runoob.com/tags/ref-standardattributes.html";
        String proxyHost="113.57.56.251";
        int proxyPort=4228;

        InetSocketAddress inetSocketAddress = new InetSocketAddress(proxyHost, proxyPort);
        Proxy.Type proxyType = Proxy.Type.HTTP;
        Proxy proxy = new Proxy(proxyType, inetSocketAddress);

        Document document = Jsoup.connect(url).proxy(proxy).ignoreContentType(true).get();
        /** 可以连着多个select,对应层次结构*/
        Elements tbody = document.select("tbody").select("tr").select("a");

        System.out.println(tbody.size());
        for (Element ele:tbody
             ) {
            System.out.println(ele);
        }

    }
}
