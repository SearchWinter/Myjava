package java8.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by anjunli on  2020/12/11
 * Stream类一些基本方法的测试案例
 **/
public class StreamDemo {

    /** 对流中的数据修改，不会改变源数据的值*/
    @Test
    public void noModify(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().forEach(i -> i += 2);
        System.out.println(list);
    }

    /**
     * filter()  保留满足条件的元素
     * count()  求元素总个数
     * collect()是一个终端操作,它接收的参数是将流中的元素累积到汇总结果的各种方式(称为收集器)
     * Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
     * */
    @Test
    public void filterTest(){
        List<String> list = Arrays.asList("tom", "cat", "","ad", "demo");
        System.out.println("List: "+list);

        List<String> filterList = list.stream().filter(x -> x.length() < 4).collect(Collectors.toList());
        System.out.println("filterList:"+filterList);

        long count = list.stream().filter(x -> x.length() < 1).count();
        System.out.println("count:"+ count);
    }

    /**
     * map() 对每一个元素进行处理
     * distinct() 去掉重复的元素
     * */
    @Test
    public void mapTest(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<Integer> collect = list.stream().map((x) -> x * 2).collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> list1 = Arrays.asList(1, 1, 2, 3, 4);
        List<Integer> collect1 = list1.stream().map(x -> x * 2).distinct().collect(Collectors.toList());
        System.out.println(collect1);
    }

    /**
     * forEach()迭代流中的每个数据
     * */
    @Test
    public void foreachTest(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        list.stream().forEach(System.out::print);
        System.out.println();
        list.stream().forEach(x->{
            System.out.print(x);
        });
    }

    /**
     * sorted() 对流中的数据进行排序 Integer 默认升序，如果是不能比较的抛出java.lang.ClassCastException
     * limit() 用于获取指定数量的流
     * */
    @Test
    public void limitTest(){
        List<Integer> list = Arrays.asList(232, 34343, 4334, 23, 3323);
        List<Integer> collect = list.stream().sorted().limit(3).collect(Collectors.toList());
        System.out.println(collect);

        List<Person> list1 = new ArrayList<>();
        list1.add(new Person("tom",21,"WH",178889));
        list1.add(new Person("cat",23,"WH",578988));
        list1.add(new Person("lz",21,"BJ",784849));
        list1.add(new Person("tm",42,"BJ",813574));
        list1.add(new Person("timo",21,"SH",999999));

        // 按年龄升序，薪资降序输出
        // reversed()降序
        // age后面也要加reversed() 不然会被后面的reversed()影响，变成降序
        list1.stream().sorted(Comparator.comparing(Person::getAge).reversed()
                .thenComparing(Person::getSalary).reversed())
                .forEach(x->{
                    System.out.println(x);
                });
    }


    @Test
    public void parallelTest(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println(count);
    }

    /**
     * mapToInt() 将流转换为IntStream
     * summaryStatistics()  返回包含这个流各种要素的流
     * */
    @Test
    public void summaryTest(){
        List<Integer> list = Arrays.asList(123, 343, 44324, 343, 23213);
        Stream<Integer> stream = list.stream();
        IntStream intStream = list.stream().mapToInt((x)->x);
        IntSummaryStatistics stats = intStream.summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }
}
