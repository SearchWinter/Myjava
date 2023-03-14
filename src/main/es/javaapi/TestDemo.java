package javaapi;

import org.apache.commons.codec.digest.DigestUtils;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

/**
 * @Description 测试ES是否连接成功,并输出查询到的值
 * @Date 2020/7/20  17:39
 **/
public class TestDemo {
    @Test
    public  void testEs() throws Exception {
        //通过工具类得到客户端
        RestHighLevelClient client = Utils.getClient();
        //创建get请求，
        GetRequest getRequest = new GetRequest("bank","10");
        //同步执行，得到getResponse
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        //输出查询到的值
        System.out.println(getResponse);
    }

    @Test
    public void JsonTest(){
        String jsonString="{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        System.out.println(jsonString);
    }

    /** 测试DigestUtils里面的加密方法*/
    @Test
    public void MDTest(){
        String  upchins = DigestUtils.md2Hex("upchina");
        System.out.println(upchins);

        String upchina = DigestUtils.md5Hex("upchina");
        System.out.println(upchina);
    }
}
