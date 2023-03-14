package structure.set;

import java.util.*;

/**
 * @ClassName HashSetDemo
 * @Description
 * @Author Li Anjun
 * @Date 2020/7/6  13:37
 **/
public class HashSetDemo {
    public static void main(String[] args) {
        //第一种直接构造,可以给定一个initialCapacity(初始容量) loadFactor(负载系数，当元素个数达到15的时候，扩容到原来的两倍)
        HashSet hashSet= new HashSet<Integer>(20,0.75f);
        HashSet<String> set = new HashSet<>(20);
        set.add("10");
        set.add("1");
        set.add("8989FASD");
        //set里面不能有重复的值，添加了两个"A",最后打印出来也只会有一个A
        set.add("A");
        System.out.println(set);
        //set也重写了toString()
        String string = set.toString();
        System.out.println(string);

        //第二种通过list构造
        //    HashSet<> hs = new HashSet<>(list);
        String[] elements=new String[]{"A","B","C","D"};
        HashSet<String> set2=new HashSet<>(Arrays.asList(elements));
//        System.out.println(set.equals(set2));

       //iterator
/*        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }*/
        //isEmpty()
//        System.out.println(set.isEmpty());

       //hashCode()
        System.out.println(set.hashCode());
        System.out.println(set2.hashCode());

        //size()
//        System.out.println(set.size());

        //contain   判断指定元素是否存在
//        System.out.println(set.contains("E"));

        //remove
//        set.remove("A");
//        System.out.println(set);

        //clone
/*        HashSet<String> setClone = new HashSet<>();
        setClone=(HashSet<String>) set.clone();
        System.out.println(setClone);*/

        //clear
        set.clear();
        System.out.println(set);
    }
}
