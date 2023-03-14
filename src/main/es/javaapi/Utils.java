package javaapi;

import javaapi.indexapi.EsConfig;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description 可以连接多个Elasticsearch
 * @Date 2020/7/20  17:29
 **/
public class Utils {
    //得到Es的客户端
    public static RestHighLevelClient getClient(){
        HttpHost httpHost = new HttpHost("localhost", 9200);
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        /** 可以有多个ES连接*/
//                        new HttpHost("localhost124", 9200, "http"),
                        httpHost
                )
        );
        return restHighLevelClient;
    }

    /** 得到ES的客户端组件 client
     *  EsConfig封装相关的参数，可以传入多个
     * */
    public static RestHighLevelClient createRestHighLevelClient(EsConfig... esConfigs) {
        HttpHost[] httpHosts =
                Arrays.stream(esConfigs).map(x -> new HttpHost(x.getHost(), x.getPort())).toArray(HttpHost[]::new);
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHosts)
                //https://blog.csdn.net/qq_38680405/article/details/107240686
                .setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
                    @Override
                    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                        return requestConfigBuilder.setConnectTimeout(10*1000) //连接超时 默认1s
                                .setSocketTimeout(300*1000) ;//套字节超时 默认30s
                    }
                }));
        return client;
    }

    /** 存入ES的时间会少8小时*/
    public static Date addTime(Date t1){
        Calendar cal=Calendar.getInstance();
        cal.setTime(t1);
        cal.add(Calendar.HOUR_OF_DAY,+8);
        return cal.getTime();
    }
}
