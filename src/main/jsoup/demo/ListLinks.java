package demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Description 从一个URL获取一个页面，提取页面中所有的连接、图片和其他辅助内容，并检查URL和文本信息
 **/
public class ListLinks {
    public static void main(String[] args)  throws IOException {
        Document document = Jsoup.connect("https://www.baidu.com/s?wd=jsoup&rsv_spt=1&rsv_iqid=0xd2b775d30005dc69&issp=1&f=8&r" +
                "sv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_dl=tb&rsv_sug2=0&rsv_btype=i&inputT=1719&rsv_sug4=5081").get();
        //获取所有的链接
        Elements links = document.select("a[href]");

        //获取所有的src
        Elements media = document.select("[src]");
        Elements imports = document.select("link[href]");

        for (Element link:links){
            System.out.println(link);
        }

/*        for(Element src:media){
            if(src.tagName().equals("img")){
                System.out.println(src.tagName()+","+src.attr("abs:src")+"\n"+src.attr("width")+","+src.attr("height"));
            }
        }*/

/*        for(Element link:imports){
            System.out.println(link);
        }*/
    }

}
