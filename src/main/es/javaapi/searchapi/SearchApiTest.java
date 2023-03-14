package javaapi.searchapi;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by anjunli on  2021/1/28
 * 查询之前的数据，添加时间戳字段后，写入新的索引(可以是不同集群)
 * f_tflag 时间 00:00 2360
 **/
public class SearchApiTest {
    public static void main(String[] args) throws IOException, ParseException {
        HttpHost httpHost = new HttpHost("localhost", 9200);
        HttpHost httpHost1 = new HttpHost("localhost", 9200);

        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHost));
        RestHighLevelClient client1 = new RestHighLevelClient(RestClient.builder(httpHost1));

        SearchRequest searchRequest = new SearchRequest("bank");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        for(int i=6;i<=10;i++) {
            searchSourceBuilder.query(QueryBuilders.termQuery("_id", i))
                    .from(0)
                    .size(2000)     //size可以大于查询结果的条数
                    .timeout(new TimeValue(60, TimeUnit.SECONDS));
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            List<Map<String, Object>> mapList = new ArrayList<>();
            SearchHit[] hits = searchResponse.getHits().getHits();
            if(hits.length==0){
                continue;
            }
            for (SearchHit hit : hits) {
                Map<String, Object> map = hit.getSourceAsMap();
                map.put("id",hit.getId());
                mapList.add(map);
                System.out.println(map);
            }

            BulkRequest bulkRequest = new BulkRequest();
            for (int j = 0; j < mapList.size(); j++) {
                //保证每条数据的id和之前一样，不会出现重复的数据
                IndexRequest indexRequest = new IndexRequest("bank").id((String)mapList.get(j).get("id"));
                mapList.get(j).remove("id");
                indexRequest.source(mapList.get(j));
                bulkRequest.add(indexRequest);
            }
            client1.bulk(bulkRequest, RequestOptions.DEFAULT);
        }
        client.close();
    }
}
