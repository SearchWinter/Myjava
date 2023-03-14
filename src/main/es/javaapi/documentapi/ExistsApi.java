package javaapi.documentapi;

import javaapi.Utils;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;

import java.io.IOException;

/**
 * @Description 如果文档存在，exists API返回true，否则返回false。
 * @Date 2020/7/21  10:26
 **/
public class ExistsApi {
    @Test
    public void existsTest() throws IOException {
        RestHighLevelClient client = Utils.getClient();
        GetRequest getRequest = new GetRequest("bank", "10000");
        //禁用获取_source。
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        //禁用获取存储字段
        getRequest.storedFields("_none_");
        //同步调用,
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);

        //判断索引存不存在
        GetIndexRequest getIndexRequest = new GetIndexRequest("logger");
        boolean exists1 = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists1);
        client.close();
    }
}
