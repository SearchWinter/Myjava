package generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Li Anjun
 * @Description 类型通配符
 * @Date 10:11 2020/7/2
 * @Param
 * @return
 **/
public class GenericTest {
    public static void main(String[] args) {
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<Integer> age = new ArrayList<>();
        ArrayList<Number> numbers = new ArrayList<>();

        name.add("cat");
        age.add(13);
        numbers.add(314);

        getData(name);
        getData(age);
        getData(numbers);

    }

    private static void getData(List<?> data){
        System.out.println("data:"+data.get(0));
    }
}
