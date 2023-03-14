import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Description JSONObject JSONArray简单应用
 * @Date 2020/8/17  17:18
 **/
public class JsonObject {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "god");
        jsonObject.put("age", 20000);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "a");
        jsonObject1.put("age", 42232);

        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("name","j3");
        jsonObject3.put("age",22);
        jsonObject3.put("address","wuhan");

//        JSONArray中不一定要存放相同的JSON对象
        JSONArray jsonArray = new JSONArray();
        //添加JSONObject
        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject3);


        System.out.println(jsonArray.size());
        //通过index对应的JSONObject
        JSONObject jsonObject2 = jsonArray.getJSONObject(0);
        System.out.println(jsonObject2);

        System.out.println(jsonArray);
    }
}
