package structure.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @ClassName TreeSetDemo
 * @Description TODO
 * @Author Li Anjun
 * @Date 2020/7/6  16:01
 **/
public class TreeSetDemo {
    public static void main(String[] args) {
        //第一种
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(100);
        ts.add(10);
        ts.add(2262);
        ts.add(226);
        //第二种
        /*String[] fruit={"apples","grapes","bnanas"};
        TreeSet<String> ts=new TreeSet<String>(Arrays.asList(fruit));*/
//        System.out.println(ts);
//        [apples, bananas, grapes]     自动排序后输出

        //ceiling       取比给定值大的第一个数，如果不存在返回null
//        System.out.println(ts.ceiling(9));

        //floor         取比给定值小的第一个数，如果不存在返回null
//        System.out.println(ts.floor(101));

        //first         取出第一个数（排序后的）
//        System.out.println(ts.first());

        //last          取出最后一个数
//        System.out.println(ts.last());

        //pollFirst     pollLast        取出第一个（最后一个数）并移除
//        System.out.println(ts.pollFirst());
//        System.out.println(ts.pollLast());

        //headSet(E toElement,boolean inclusive)   tailSet
        // 输出的值，最大（小）为多少，  inclusive 包不包含该值
//        System.out.println(ts.headSet(100,true));
//        System.out.println(ts.tailSet(100,true));

        //lower(E e)        找出比给定值小的值当中最大的值
//        System.out.println(ts.lower(101));

        //subSet        给定范围的值
//        System.out.println(ts.subSet(100, true, 300, true));

        //descendingIterator        逆序的迭代器
        Iterator<Integer> iterator = ts.descendingIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
