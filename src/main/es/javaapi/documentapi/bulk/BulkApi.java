package javaapi.documentapi.bulk;


import javaapi.Utils;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;


import java.io.IOException;

/**
 * @Description BulkRequest可用于使用单个请求执行多个索引、更新和/或删除操作。
 * @Date 2020/7/21  16:21
 **/
public class BulkApi {
    @Test
    public void BuleTest() throws IOException {
        RestHighLevelClient client = Utils.getClient();
        //创建一个BulkRequest
        BulkRequest bulkRequest = new BulkRequest();
        //添加三个indexRequest
        /** 批量API只支持用JSON或SMILE编码的文档。提供任何其他格式的文档都会导致错误。*/
        bulkRequest.add(new IndexRequest("posts").id("1").source(XContentType.JSON,"field","foo1","name","cat"));
        bulkRequest.add(new IndexRequest("posts").id("2").source(XContentType.JSON,"field","foo2","name","dog"));
        bulkRequest.add(new IndexRequest("posts").id("3").source(XContentType.JSON,"field","foo3","name","pig"));

        /** 不同类型的request也可以放到同一个bulkRequest里面*/
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest("posts", "3"));
        request.add(new UpdateRequest("posts", "2")
                .doc(XContentType.JSON,"other", "test"));
        request.add(new IndexRequest("posts").id("4")
                .source(XContentType.JSON,"field", "baz"));

        //同步执行
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
//        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);

    }

    /** BulkProcessor.builder方法测试*/
    @Test
    public void BulkProTest(){
        RestHighLevelClient client = Utils.getClient();
        //创建BulkProcessor.Listener
        BulkProcessor.Listener listener = new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long l, BulkRequest bulkRequest) {
                //在每个BulkRequest执行之前调用
            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {
                //在每个BulkRequest执行之后调用
            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {
                //当BulkRequest失败时调用
            }
        };

        //通过从BulkProcessor. builder中调用build()方法来创建BulkProcessor。RestHighLevelClient.bulkAsync()方法将用于在内部执行BulkRequest。
        BulkProcessor bulkProcessor = BulkProcessor.builder(
                (bulkRequest, bulkListener) ->
                        client.bulkAsync(bulkRequest, RequestOptions.DEFAULT, bulkListener),
                listener).build();


        //BulkProcessor.Builder提供了一些方法来配置BulkProcessor应该如何处理请求执行
        BulkProcessor.Builder builder = BulkProcessor.builder(
                (request, bulkListener) ->
                        client.bulkAsync(request, RequestOptions.DEFAULT, bulkListener),
                listener);
        //根据当前添加的动作数量设置刷新新批量请求的时间(默认为1000，使用-1禁用它)
        builder.setBulkActions(500);
        //根据当前添加的操作的大小设置刷新新批量请求的时间(默认为5Mb，使用-1禁用它)
        builder.setBulkSize(new ByteSizeValue(1L, ByteSizeUnit.MB));
        //设置允许并发执行的请求数(默认为1，使用0只允许执行单个请求)
        builder.setConcurrentRequests(0);
        //设置刷新间隔，如果间隔通过，则刷新任何挂起的BulkRequest(默认未设置)
        builder.setFlushInterval(TimeValue.timeValueSeconds(10L));
        //设置一个常量后退策略，最初等待1秒，然后重试3次。
        builder.setBackoffPolicy(BackoffPolicy.constantBackoff(TimeValue.timeValueSeconds(1L), 3));

    }
}
