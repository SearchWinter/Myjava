package structure.map.ConcHashMap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by anjunli on  2020/11/12
 **/
public class Demo {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> chashMap = new ConcurrentHashMap<>();

        //key相同，value不同，改变value的值
        chashMap.put("Base.log", 1);
        chashMap.put("Base.log", 2);
        chashMap.put("taf.log",1);
        chashMap.put("app.log",1);

        System.out.println(chashMap.get("Base.log"));

        //判断指定key存在
        boolean b = chashMap.containsKey("demo.log");
        System.out.println(b);
        System.out.println(chashMap);

        //移除相关的数据
        chashMap.remove("app.log");
        System.out.println(chashMap);
    }
}
