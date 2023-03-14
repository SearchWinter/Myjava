package javaapi.documentapi;

import javaapi.Utils;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;

/**
 * @ClassName UpdateApi
 * @Description elasticsearch updateApi示例
 * @Author Li Anjun
 * @Date 2020/7/21  11:02
 **/
public class UpdateApi {
    @Test
    public void UpdateTest() throws IOException {
        RestHighLevelClient client = Utils.getClient();
        //创建一个updateRequest
        UpdateRequest updateRequest = new UpdateRequest("logger", "4");

        //使用部分文档进行更新
        /** JSON格式的字符串*/
/*        String jsonstring="{"+
                "\"user\":\"kill\","+
                "\"postDate\":\"2019-10-10\","+
                "\"message\":\"trying elasticsearch updateapi\""+"}";
        updateRequest.doc(jsonstring, XContentType.JSON);*/

        /**如果只有message一条信息，就只会对message进行修改*/
         /*String jsonstring="{"+
         		"\"message\":\"trying\""+"}";
         updateRequest.doc(jsonstring, XContentType.JSON);
        */

        /** 部分文档源以映射的形式提供，可以自动转换为JSON格式*/
/*        HashMap<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user","kill");
        jsonMap.put("postDate",new Date());
        jsonMap.put("message","trying elasticsearch updateapi");
        updateRequest.doc(jsonMap);*/

        /**作为XContentBuilder对象提供的部分文档源，Elasticsearch内置帮助程序生成JSON内容*/
/*        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user","kill");
            builder.timeField("postDate",new Date());
            builder.field("message","trying elasticsearch updateapi");
        }
        builder.endObject();
        updateRequest.doc(builder);*/

        /** 部分文档源以对象键对的形式提供，将其转换为JSON格式*/
/*        updateRequest.doc(
                "user","kill",
                "postDate",new Date(),
                "message","trying elasticsearch update api"
                );
        */
        /** upsert方法 是没有id为2的文档的*/
        String jsonstring ="{"+
                "\"user\":\"kill\","+
                "\"postDate\":\"2020-02-02\","+
                "\"message\":\"trying elasticsearch updateapi test3\""+"}";
        updateRequest.doc(jsonstring,XContentType.JSON);
        updateRequest.docAsUpsert(true);
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse);
        client.close();
    }
}
