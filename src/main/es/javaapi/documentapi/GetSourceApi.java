package javaapi.documentapi;

import javaapi.Utils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.GetSourceRequest;
import org.elasticsearch.client.core.GetSourceResponse;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.util.Map;

/**
 * @ClassName GetSourceApi
 * @Description 这个API只获取文档的_source字段
 * @Author Li Anjun
 * @Date 2020/7/20  18:49
 **/
public class GetSourceApi {
    public static void main(String[] args) throws Exception {
        //得到Client
        RestHighLevelClient client = Utils.getClient();

        //创建一个GetSourceRequest
        GetSourceRequest getSourceRequest = new GetSourceRequest("bank", "1");

        //FetchSourceContext  输出的结果里面不包含指定的字段
        //includes（包含）、excludes(排除)。
        String[] includes = Strings.EMPTY_ARRAY;
        String[] excludes = {"firstname", "address"};
        getSourceRequest.fetchSourceContext(new FetchSourceContext(true, includes, excludes));

        //同步执行
        GetSourceResponse getSourceResponse = client.getSource(getSourceRequest, RequestOptions.DEFAULT);

        //输出结果
        System.out.println(getSourceResponse);

        //get source response
        Map<String, Object> source = getSourceResponse.getSource();
        System.out.println(source);
        //关闭资源
        client.close();
    }
}
