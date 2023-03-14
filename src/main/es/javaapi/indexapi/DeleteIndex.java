package javaapi.indexapi;

import javaapi.Utils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @Description 删除索引
 * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.9/java-rest-high-delete-index.html#java-rest-high-delete-index
 * @Date 2020/9/28  16:11
 **/
public class DeleteIndex {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = Utils.getClient();

//        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("twitter");
        //可以使用通配符
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("twitt*_demo");

        client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);

        client.close();
    }
}
