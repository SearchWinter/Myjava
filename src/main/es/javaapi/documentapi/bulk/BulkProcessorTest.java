package javaapi.documentapi.bulk;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.TimeUnit;

/** @Description TODO @Date 2020/9/28 9:29 */
public class BulkProcessorTest {
    public static final String DEFAULT_FIELD_PRE = "var";
    public static final String DEFAULT_INDEX_NAME="bulk_test";

    public static void main(String[] args) throws IOException {
    HttpHost httpHost = new HttpHost("localhost", 9200);
    RestHighLevelClient restHighLevelClient =
        new RestHighLevelClient(
            RestClient.builder(
                /** 可以有多个ES连接 */
                //                        new HttpHost("localhost", 9200, "http"),
                httpHost));

    long start = System.currentTimeMillis();

    Path path = Paths.get("data/Base.LoginServer_stat_20200701.log");
    FileChannel open = FileChannel.open(path, StandardOpenOption.READ);
    BufferedReader bufReader = new BufferedReader(Channels.newReader(open, "UTF-8"));

    List<String> lines = new ArrayList<String>();

      bufReader.lines().forEach( line->{
              lines.add(line);
              if(lines.size()>=1000){
                  for (int i = 0; i < lines.size(); i++) {
                      Map<String, String> data = new HashMap<String, String>();

                      String[] splits = lines.get(i).split("\\|", -1);
                      for (int j = 0; j < splits.length; j++) {
                          String fieldName = DEFAULT_FIELD_PRE + (j + 1);
                          data.put(fieldName, splits[j]);
                      }
                      getBulkProcessor(restHighLevelClient,getBulkListener()).add(new IndexRequest(DEFAULT_INDEX_NAME).source(data));
                  }
              }
        });
      Map<String, String> m = new HashMap<>();
    System.out.println("bulkProcessor 执行时间： " + (System.currentTimeMillis() - start));

    BulkRequest bulkRequest = new BulkRequest();
    bulkRequest.setRefreshPolicy("wait_for");
    bulkRequest.pipeline("pipelineId");

    restHighLevelClient.close();
  }

  /** 得到BulkProcessor.Listener */
  public static BulkProcessor.Listener getBulkListener() {
    BulkProcessor.Listener listener =
        new BulkProcessor.Listener() {
          @Override
          public void beforeBulk(long executionId, BulkRequest request) {
            // 在每次执行BulkRequest之前调用此方法
            System.out.println( "序号：{" + executionId + "} ，开始执行 {" + request.numberOfActions() + "} 条数据批量操作。");
          }

          @Override
            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                // 在每次执行BulkRequest后调用，通过此方法可以获取BulkResponse是否包含错误
                if (response.hasFailures()) {
                    System.out.println("Bulk {"+executionId+ "} executed with failures");
                } else {
                    System.out.println("序号：{"+executionId+"} ，执行 {"+request.numberOfActions()+"} 条数据批量操作成功，共耗费{"+response.getTook().getMillis()+"}毫秒。");
                }
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                // 当BulkRequest失败时调用此方法
                System.out.println("序号：{"+executionId+"} 批量操作失败，总记录数：{"+request.numberOfActions()+"} ，报错信息为：{"+failure.getMessage()+"}");
            }
        };

    return listener;
  }


  public static BulkProcessor getBulkProcessor(
      RestHighLevelClient client, BulkProcessor.Listener listener) {
    BulkProcessor bulkProcessor =
        BulkProcessor.builder(
                (request, bulkListener) -> client.bulkAsync(request, RequestOptions.DEFAULT, bulkListener), listener)
            // 每添加n个request，执行一次bulk操作  默认1000
            .setBulkActions(1000)
            // 请求的size达到5M时，执行一次bulk操作 默认5M
            .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
            // 每5s执行一次bulk操作
            .setFlushInterval(TimeValue.timeValueSeconds(5))
            // 设置并发请求数。默认是1，表示允许执行1个并发请求，积累bulk
            // requests和发送bulk是异步的，其数值表示发送bulk的并发线程数（可以为2、3、...）；若设置为0表示二者同步。
            .setConcurrentRequests(1)
            // 最大重试次数为3次，启动延迟为100ms。
            .setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
            .build();
    return bulkProcessor;
  }
}
