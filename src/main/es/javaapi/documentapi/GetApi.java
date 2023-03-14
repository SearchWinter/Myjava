package javaapi.documentapi;

import javaapi.Utils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Cancellable;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import java.io.IOException;
/**
 * @ClassName GetApi
 * @Description TODO
 * @Author Li Anjun
 * @Date 2020/7/20  15:53
 **/
public class GetApi {
    /** 通过同步执行的方法，查询数据并输出*/
    @Test
    public void SyncTest() throws IOException {
        //得到RestHighLevelClient
        RestHighLevelClient client = Utils.getClient();
        //创建一个get请求
        GetRequest getRequest = new GetRequest("bank", "10");
        //同步执行
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse);
    }

        //异步执行
        @Test public void AsyncTest(){
            RestHighLevelClient client = Utils.getClient();
            ActionListener<GetResponse> getListener = new ActionListener<GetResponse>(){
            //匿名函数
            /** 当执行成功的时候调用*/
            @Override
            public void onResponse(GetResponse o) {
                System.out.println("查找成功");
            }
            /** 当执行失败的时候调用*/
            @Override
            public void onFailure(Exception e) {
                System.out.println("查找失败");
            }
        };
        GetRequest getRequest2 = new GetRequest("bank","10000");
        Cancellable async = client.getAsync(getRequest2, RequestOptions.DEFAULT, getListener);
        System.out.println(async.toString());
    }
}
