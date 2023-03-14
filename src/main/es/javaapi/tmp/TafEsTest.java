package javaapi.tmp;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * Created by anjunli on  2021/4/13
 **/
public class TafEsTest {
    public static void main(String[] args) {
        HttpHost httpHost = new HttpHost("localhost", 9200);
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(httpHost)
        );


    }
}
