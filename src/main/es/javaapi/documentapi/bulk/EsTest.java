package javaapi.documentapi.bulk;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description TODO
 * @Date 2020/9/27  10:24
 **/
public class EsTest {
    public static final String DEFAULT_FILED_NAME = "ApplicationServerName";
    public static final String DEFAULT_FIELD_PRE = "var";

    public static void main(String[] args) throws IOException {
        HttpHost httpHost = new HttpHost("192.168.8.237", 9200);
        HttpHost httpHost2 = new HttpHost("localhost.174", 9200);

        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        /** 可以有多个ES连接*/
//                        new HttpHost("localhost", 9200, "http"),
                        httpHost2
                )
        );


        Path path = Paths.get("data/Base.LoginServer_stat_20200701.log");

        long openFile = System.currentTimeMillis();
        FileChannel open = FileChannel.open(path, StandardOpenOption.READ);
//        System.out.println("打开文件 :" + (System.currentTimeMillis() - openFile));

        BufferedReader bufReader = new BufferedReader(Channels.newReader(open, "UTF-8"));

        List<String> lines = new ArrayList<String>();
        List<Map<String, ?>> datas = new ArrayList<Map<String, ?>>();

        bufReader.lines().forEach(line -> {
            lines.add(line);
        });

                long start2 = System.currentTimeMillis();
                for (int i = 0; i < lines.size(); i++) {
                    Map<String, String> data = new HashMap<String, String>();

                    String[] splits = lines.get(i).split("\\|", -1);
                    for (int j = 0; j < splits.length; j++) {
                        String fieldName = DEFAULT_FIELD_PRE + (j + 1);
                        data.put(fieldName, splits[j]);
                    }
                    datas.add(data);
                }
//                System.out.println("数据转换为Map:" + (System.currentTimeMillis() - start2));

                insert(datas, restHighLevelClient);
                lines.clear();
//        System.out.println("lines.size():"+lines.size());
        open.close();
        restHighLevelClient.close();
    }

    public static void insert(List<Map<String, ?>> datas, RestHighLevelClient restHighLevelClient) {

        BulkRequest bulkRequest = new BulkRequest();

        long Start1 = System.currentTimeMillis();
        for (int i = 0; i < datas.size(); i++) {
            IndexRequest indexRequest = new IndexRequest("es_test3");
            indexRequest.source(datas.get(i));
            bulkRequest.add(indexRequest);
        }
        System.out.println("bulk中请求数："+datas.size());
//        System.out.println("添加indexRequest :" + (System.currentTimeMillis() - Start1));

        long BulkStart = System.currentTimeMillis();
        try {
            System.out.println("执行bulk");
            restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("bulk 执行时间: " + (System.currentTimeMillis() - BulkStart));
        System.out.println("********************");
    }
}
