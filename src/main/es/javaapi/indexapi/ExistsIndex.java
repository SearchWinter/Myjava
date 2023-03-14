package javaapi.indexapi;

import javaapi.Utils;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;

import java.io.IOException;

/**
 * Created by anjunli on  2020/11/4
 **/
public class ExistsIndex {
  public static void main(String[] args) throws IOException {
      RestHighLevelClient client = Utils.getClient();
      //索引名称必须都是小写
      String indexName="twitt*_demo";

      //判断索引是否存在
      GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
      boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
    System.out.println(exists);
    client.close();
  }
}
