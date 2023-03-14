package structure.map.hashmapdemo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by anjunli on  2020/12/14
 *
 **/
public class MapToJson {

    /** 将HashMap转换为JSON字符串*/
    @Test
    public void MapToJsonTest(){
        HashMap<String, String> hashMap = new HashMap<>(10, 0.75f);
        hashMap.put("tom","cat");
        hashMap.put("map","demo");
        hashMap.put("json","test");

        System.out.println(hashMap);
        Object json = JSONObject.toJSON(hashMap);
        System.out.println(json);

        HashMap<String, String> hashMap2 = new HashMap<>(10, 0.75f);
        hashMap2.put("tom","cat2");
        hashMap2.put("map","map2");
        hashMap2.put("json","json2");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(hashMap);
        jsonArray.add(hashMap2);
        System.out.println(jsonArray);
    }

    /** */
    @Test
    public void JsonToMap(){
        String jsonStr="{\"json\":\"test\",\"map\":\"demo\",\"tom\":\"cat\"}";
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        System.out.println(jsonObject);
    }

}
