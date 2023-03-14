import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

/**
 * Created by anjunli on  2020/12/16
 **/
public class Demo {
    @Test
    public void toJsonObject() throws JSONException {
        //创建一个json对象，并添加数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tom","cat");
        jsonObject.put("json","demo");
        System.out.println(jsonObject);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("tom2","cat");
        jsonObject1.put("json2","demo");

        //创建JSON数组
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        jsonArray.put(jsonObject1);

        System.out.println(jsonArray);

        /** JSONArray index从0开始
         *  get() 得到其中的JSONObject 要强制转换
         * */
        JSONObject o = (JSONObject)jsonArray.get(0);
        System.out.println(o);
        System.out.println(o.get("tom"));
    }
}
