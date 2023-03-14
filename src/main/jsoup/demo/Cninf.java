package demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @Description TODO
 **/
public class Cninf {
    public static void main(String[] args) throws IOException {
        //通过URL得到连接，使用get()得到document
        Document document = Jsoup.connect("http://webapi.cninfo.com.cn/api/sysapi/p_sysapi1018?scode=300158")
                .header("Accept","application/json, text/javascript, */*; q=0.01")
                .header("Accept-Encoding","gzip, deflate")
                .header("Accept-Language","zh-CN,zh;q=0.9")
                .header("Connection","keep-alive")
                .header("Content-Length","0")
                .header("Cookie","__qc_wId=192; pgv_pvid=6140435100; Hm_lvt_489bd07e99fbfc5f12cbb4145adb0a9b=1596159582; codeKey=befccf416f; Hm_lpvt_489bd07e99fbfc5f12cbb4145adb0a9b=1596161260")
                .header("Host","webapi.cninfo.com.cn")
                .header("mcode","MTU5NjE2NDc0MB==")
                .header("Origin","http://webapi.cninfo.com.cn")
                .header("Referer","http://webapi.cninfo.com.cn/")
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36")
                .header("X-Requested-With","XMLHttpRequest")
                .ignoreContentType(true)
                .get();
        System.out.println(document);


    }
}
