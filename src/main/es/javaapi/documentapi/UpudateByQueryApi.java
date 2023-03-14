package javaapi.documentapi;

import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.junit.Test;

/**
 * @Description TODO
 * @Date 2020/7/23  14:54
 **/
public class UpudateByQueryApi {
    @Test
    public void UBQTest(){

        //参数中的index可以写多个
        UpdateByQueryRequest request = new UpdateByQueryRequest("posts");

        //可以通过添加查询来限制文档
//        request.setQuery(new TermQueryBuilder(""))
    }
}
