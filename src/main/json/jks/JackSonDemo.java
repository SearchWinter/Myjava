package jks;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by anjunli on  2022/9/23
 * Jackson ObjectMapper示例
 * https://juejin.cn/post/6844904166809157639
 * git:https://github.com/FasterXML/jackson
 * git:https://github.com/FasterXML/jackson-core
 **/
public class JackSonDemo {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 , \"door\" : 10 }";
        try {
            //从JSON反序列化JAVA对象
            Car car = objectMapper.readValue(carJson, Car.class);
            System.out.println(car.getBrand());
            System.out.println(car.getDoors());

            //JAVA对象序列化
//            objectMapper.

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
        try {
            //JSON数组字符串-->Java对象数组
            Car[] cars = objectMapper.readValue(jsonArray, Car[].class);

            //JSON数组字符串-->List
            List<Car> list = objectMapper.readValue(jsonArray, new TypeReference<List<Car>>() {
            });
            System.out.println(list);

            //JSON字符串-->Map
            Map<String, Object> jsonMap = objectMapper.readValue(jsonArray, new TypeReference<Map<String, Object>>() {
            });


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 忽略未知的JSON字段
     * 有时候，与要从JSON读取的Java对象相比，JSON中的字段更多。 默认情况下，Jackson在这种情况下会抛出异常，报不知道XYZ字段异常，因为在Java对象中找不到该字段。
     * 但是，有时应该允许JSON中的字段多于相应的Java对象中的字段，在这种情况下，可以使用Jackson配置忽略这些额外的字段。
     */
    @Test
    public void tset3() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        String carJson2 = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 , \"door\" : 10 }";

        try {
            Car car = objectMapper.readValue(carJson2, Car.class);
            System.out.println(car);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test4(){
        JsonFactory factory = JsonFactory.builder()
                .enable(JsonReadFeature.ALLOW_JAVA_COMMENTS)
                .build();

        JsonFactory jsonFactory = new JsonFactory();

    }

    /**
     * 使用ObjectMapper拼接json文本
     * */
    @Test
    public void test5() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("obj1","value1");
        rootNode.put("obj2","value2");
        //简单示例
        System.out.println(rootNode);

        ObjectNode childNode1 = objectMapper.createObjectNode();
        childNode1.put("age3","value3");
        childNode1.put("address3","value3");
        rootNode.put("obj3",childNode1);
        //key-value value 为json对象
        System.out.println(rootNode);

        ObjectNode childNode2= objectMapper.createObjectNode();
        childNode2.put("age4","value4");
        childNode2.put("address4","value4");
        rootNode.put("obj4",childNode2);

        //json数组
        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add(childNode1);
        arrayNode.add(childNode2);
        rootNode.put("obj5",arrayNode);
        //key-value value 为json数组
        System.out.println(rootNode);

        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        System.out.println(jsonString);

        ObjectNode jsonNodes = objectMapper.readValue(jsonString, ObjectNode.class);
        System.out.println(jsonNodes.get("obj4"));
        System.out.println(jsonNodes);

    }
}
