package javaapi.documentapi;

import javaapi.Utils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import java.io.IOException;
import java.util.Date;

/**
 * @ClassName esclient
 * @Description    elasticserch indexAPI示例
 * @Author Li Anjun
 * @Date 2020/7/20  9:51
 **/
public class IndexApi {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = Utils.getClient();
        /** 构建indexRequest，可以指定id*/
        IndexRequest indexRequest = new IndexRequest("logger").id("1");
        //数据源
        indexRequest.source(
                "user","kimchy",
                "postDate",new Date(),
                "message","trying elasticsearch indesRequest"
        );
        //以同步的方式执行
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse);

        restHighLevelClient.close();
    }
}
