package javaapi.documentapi;


import javaapi.Utils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

/**
 * @Description   删除索引中的文档
 * @Date 2020/7/21  10:43
 **/
public class DeleteApi {
    @Test
    public void DeleteTest() throws Exception{
        RestHighLevelClient client = Utils.getClient();
        //创建一个deleteRequest
        DeleteRequest deleteRequest = new DeleteRequest("logger", "1");
        //同步执行 synchronous
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);

        System.out.println(deleteResponse);
    }

}
