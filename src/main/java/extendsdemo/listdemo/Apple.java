package extendsdemo.listdemo;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName Apple
 * @Description TODO
 * @Author Li Anjun
 * @Date 2020/7/13  18:25
 **/
public class Apple extends Fruit {
    public static void main(String[] args) {
        List<? extends Fruit> list= new LinkedList<>();
//        list.add(new Fruit());

    }
}
