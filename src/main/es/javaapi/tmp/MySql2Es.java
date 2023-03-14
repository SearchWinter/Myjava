package javaapi.tmp;

import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author up-517
 * @date 2020/12/01
 */
public class MySql2Es {
    public static final String INDEX_NAME = "call_chain";

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        HttpHost httpHost = new HttpHost("localhost", 9200);

        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(
            /** 可以有多个ES连接 */
            // new HttpHost("localhost", 9200, "http"),
            httpHost));

        GetIndexRequest getIndexRequest = new GetIndexRequest(INDEX_NAME);
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        if (!exists) {
            CreateIndexRequest createIndex = new CreateIndexRequest(INDEX_NAME);
            // 索引的相关设置
            createIndex.settings(Settings.builder().put("index.number_of_shards", 5) // 分片数
                .put("index.number_of_replicas", 0) // 分片副本数，默认1
            /*                .put("refresh_interval", TimeValue.timeValueSeconds(20))         //刷新间隔，默认1s，-1禁用
                .put("max_result_window",50000)     // from+size 最大值，默认10000
                .put("index.translog.durability","async")
                .put("index.translog.sync_interval",TimeValue.timeValueSeconds(20))
                .put("index.translog.flush_threshold_size","50mb")
            
                .put("index.search.slowlog.threshold.query.warn",TimeValue.timeValueSeconds(10))
                .put("index.search.slowlog.threshold.query.info",TimeValue.timeValueSeconds(5))
                .put("index.search.slowlog.threshold.query.debug",TimeValue.timeValueSeconds(2))
                .put("index.search.slowlog.threshold.query.trace",TimeValue.timeValueMillis(500))
            
                .put("index.search.slowlog.threshold.fetch.warn",TimeValue.timeValueSeconds(1))
                .put("index.search.slowlog.threshold.fetch.info",TimeValue.timeValueMillis(800))
                .put("index.search.slowlog.threshold.fetch.debug",TimeValue.timeValueMillis(500))
                .put("index.search.slowlog.threshold.fetch.trace",TimeValue.timeValueMillis(200))
                .put("index.search.slowlog.level","info")*/
            // 连接到es节点的超时时间

            );
            // createIndex.setMasterTimeout(TimeValue.timeValueSeconds(30));
            restHighLevelClient.indices().create(createIndex, RequestOptions.DEFAULT);
        }
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/db_base?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8",
            "xxx", "xxx");
        try {
            Statement statement = connection.createStatement();
            try {
                ResultSet rs = statement.executeQuery("select * from t_call_chain_data");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
                try {
                    MapListHandler mapListHandler = new MapListHandler();
                    // List<Map<String, Object>> datas = mapListHandler.handle(rs);
                    List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
                    while (rs.next()) {
                        Map<String, Object> data = new HashMap<String, Object>();
                        int f_id = rs.getInt("f_id");
                        data.put("f_id", f_id);
                        String f_client = rs.getString("f_client");
                        data.put("f_client", f_client);
                        String f_server = rs.getString("f_server");
                        data.put("f_server", f_server);
                        String f_func = rs.getString("f_func");
                        data.put("f_func", f_func);
                        String f_ip = rs.getString("f_ip");
                        data.put("f_ip", f_ip);
                        long f_cost = rs.getLong("f_cost");
                        data.put("f_cost", f_cost);
                        long f_qtime = rs.getLong("f_qtime");
                        data.put("f_qtime", f_qtime);
                        String f_chain_key = rs.getString("f_chain_key");
                        data.put("f_chain_key", f_chain_key);
                        String f_cckey = rs.getString("f_cckey");
                        data.put("f_cckey", f_cckey);
                        String f_req_json = rs.getString("f_req_json");
                        data.put("f_req_json", f_req_json);
                        String f_rsp_json = rs.getString("f_rsp_json");
                        data.put("f_rsp_json", f_rsp_json);
                        int f_is_start = rs.getInt("f_is_start");
                        data.put("f_is_start", f_is_start);
                        Timestamp timestamp = rs.getTimestamp("f_update_time");
                        String f_update_time = sdf.format(timestamp);
                        data.put("f_update_time", f_update_time);
                        datas.add(data);
                    }
                    insert(datas, restHighLevelClient);
                } finally {
                    rs.close();
                }
            } finally {
                statement.close();
            }
        } finally {
            connection.close();
        }
        restHighLevelClient.close();
    }

    public static void insert(List<Map<String, Object>> datas, RestHighLevelClient restHighLevelClient) {

        BulkRequest bulkRequest = new BulkRequest();
        for (int i = 0; i < datas.size(); i++) {
            IndexRequest indexRequest = new IndexRequest(INDEX_NAME);
            indexRequest.source(datas.get(i));
            bulkRequest.add(indexRequest);
        }
        try {
            System.out.println("执行bulk");
            restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
