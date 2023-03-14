package javaapi.documentapi;

import javaapi.Utils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @Description multiGet API在一个http请求中并行执行多个get请求。
 * @Date 2020/7/22  15:54
 **/
public class MultiGetApi {
    @Test
    public void MultiGetTest() throws IOException {
        RestHighLevelClient client = Utils.getClient();
        //创建一个MultiGetRequest
        MultiGetRequest multiGetRequest = new MultiGetRequest();
        //添加要获取的项  索引，documentID
        multiGetRequest.add(new MultiGetRequest.Item("posts", "1"));
        multiGetRequest.add(new MultiGetRequest.Item("posts","2"));


        //fetchSourceContext  指定查询的结果包含哪些字段,将includes和excludes交换就是不包含
/*
        String[] includes = {"field"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        multiGetRequest.add(new MultiGetRequest.Item("posts","3").fetchSourceContext(fetchSourceContext));
*/
        //执行
        MultiGetResponse multiGetItemResponses = client.mget(multiGetRequest, RequestOptions.DEFAULT);
        System.out.println(multiGetItemResponses);
    }

    /** 查询特定存储字段的值*/
    @Test
    public void StoredMGetTest() throws IOException{
        RestHighLevelClient client = Utils.getClient();
        MultiGetRequest multiGetRequest = new MultiGetRequest();
        //为特定的存储字段配置检索(要求字段在映射中单独存储)
        multiGetRequest.add(new MultiGetRequest.Item("posts","1").storedFields("field"));

        MultiGetResponse mget = client.mget(multiGetRequest, RequestOptions.DEFAULT);
        //取其中第一个request
        MultiGetItemResponse item = mget.getResponses()[0];
        GetResponse response = item.getResponse();
        Map<String, DocumentField> fields = response.getFields();
        System.out.println(fields);
    }
}
