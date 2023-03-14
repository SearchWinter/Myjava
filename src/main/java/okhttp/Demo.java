package okhttp;

import okhttp3.*;

import java.io.IOException;

/**
 * Created by anjunli on  2020/12/9
 **/
public class Demo {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        String nowDate="20210202";
        String label="Test.Server1Server";
        String content="{\"query\":\"select * from taf_call_server_"+nowDate+" where label = \'"+label+"\'\"}";
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url("http://localhost:9200/_sql?format=json")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        String string = response.body().string();
        System.out.println(string);
    }
}
