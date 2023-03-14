package javaapi.indexapi;

import javaapi.Utils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;

/**
 * @Description 创建索引，并配置分片数
 * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.9/java-rest-high-create-index.html#java-rest-high-create-index-sync
 * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.9/java-rest-high-indices-exists.html
 * @Date 2020/8/5  16:38
 **/
public class CreateIndex {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = Utils.getClient();
        //索引名称必须都是小写
        String indexName="cursor-test";

        //判断索引是否存在
        GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);

        //不存在就创建索引
        if (!exists) {
            CreateIndexRequest createIndex = new CreateIndexRequest(indexName);
            //索引的相关设置
            createIndex.settings(Settings.builder()
                    //分片数
                    .put("index.number_of_shards", 5)
                    //分片副本数，默认1。初次导入数据时设为0，加快写入速度
                    .put("index.number_of_replicas", 0)
                    // from+size 最大值，默认10000
                    .put("max_result_window",10000)

                    //调优参数
                    //刷新间隔，默认1s，-1禁用
                    .put("refresh_interval",TimeValue.timeValueSeconds(1))
                    .put("index.translog.durability","async")
                    .put("index.translog.flush_threshold_size","50mb")
                    .put("index.translog.sync_interval",TimeValue.timeValueSeconds(120))

                    .put("index.search.slowlog.threshold.query.warn",TimeValue.timeValueSeconds(10))
                    .put("index.search.slowlog.threshold.query.info",TimeValue.timeValueSeconds(5))
                    .put("index.search.slowlog.threshold.query.debug",TimeValue.timeValueSeconds(2))
                    .put("index.search.slowlog.threshold.query.trace",TimeValue.timeValueMillis(500))
                    .put("index.search.slowlog.threshold.fetch.warn",TimeValue.timeValueSeconds(1))
                    .put("index.search.slowlog.threshold.fetch.info",TimeValue.timeValueMillis(800))
                    .put("index.search.slowlog.threshold.fetch.debug",TimeValue.timeValueMillis(500))
                    .put("index.search.slowlog.threshold.fetch.trace",TimeValue.timeValueMillis(200))
                    .put("index.search.slowlog.level","info")
            );
            //连接到es节点的超时时间
            createIndex.setMasterTimeout(TimeValue.timeValueSeconds(30));
            client.indices().create(createIndex, RequestOptions.DEFAULT);
        }
        client.close();
    }

    /** 创建索引时，指定映射*/
    @Test
    public void testMapping() throws IOException {
        HttpHost httpHost = new HttpHost("localhost", 9200);
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHost));
        String indexName="mapping-test";
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);

        createIndexRequest.settings(Settings.builder()
//                .put()
        );
        String str ="{ \"settings\" : {\n" +
                "        \"index\" : {\n" +
                "            \"number_of_shards\" : 5, \n" +
                "            \"number_of_replicas\" : 0 ,\n" +
                "            \"refresh_interval\":\"10s\",\n" +
                "            \"translog.durability\":\"async\",\n" +
                "            \"translog.sync_interval\": \"20s\",\n" +
                "            \"translog.flush_threshold_size\":\"50mb\"\n" +
                "        }\n" +
                "    },\n" +
                "  \"mappings\": {\n" +
                "      \"properties\": {\n" +
                "        \"stattime\": {\n" +
                "          \"type\":  \"date\"\n" +
                "        },\n" +
                "       \"f_date\": {\n" +
                "          \"type\":  \"date\"\n" +
                "        },\n" +
                "       \"f_tflag\": {\n" +
                "          \"type\":  \"text\",\n" +
                "          \"fields\":{\n" +
                "          \t\"keyword\":{\n" +
                "          \t\t\"type\":\"keyword\"\n" +
                "          \t}\n" +
                "          },\n" +
                "          \"index\": \"true\",\n" +
                "          \"analyzer\": \"keyword\",\n" +
                "          \"search_analyzer\": \"keyword\",\n" +
                "          \"fielddata\": true   \n" +
                "        },\n" +
                "        \"slave_port\":{\n" +
                "        \t\"type\":\"integer\",\n" +
                "        \t\"index\":\"true\"\n" +
                "        },\n" +
                "        \"total_time\":{\n" +
                "        \t\"type\":\"long\",\n" +
                "        \t\"index\":\"true\"\n" +
                "        }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        createIndexRequest.mapping(
                str,
                XContentType.JSON

        );
        client.indices().create(createIndexRequest,RequestOptions.DEFAULT);
        client.close();
    }
}
