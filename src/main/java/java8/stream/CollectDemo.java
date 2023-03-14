package java8.stream;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by anjunli on  2020/12/16
 * Stream类的Collectors()方法案例
 **/
public class CollectDemo {
    @Test
    public void collectorsTest(){
        List<String> list = Arrays.asList("tom", "cat", "java", "scala","java");
        /** Collectors.toList() 转换成List集合*/
        List<String> collect = list.stream().collect(Collectors.toList());
        System.out.println(collect);

        /** Collectors.toSet()  转换为Set集合,会去重*/
        Set<String> set = list.stream().collect(Collectors.toSet());
        System.out.println(set);

        /** Collectors.toMap(keyMapper,valueMapper,mergeFunction)  转换为map
         *  mergeFunction 有重复数据时如何处理,否则 java.lang.IllegalStateException: Duplicate key java */
        Map<String, String> map = list.stream().collect(Collectors.toMap(x -> x, x -> x + "value",(oldVal,newVal)->newVal));
        System.out.println(map);
        Map<String, String> map1 = list.stream().collect(Collectors.toMap(x -> x, x -> x + "value"));
        System.out.println(map1);
    }

    @Test
    public void collectorsTest2(){
        List<Integer> list = Arrays.asList(213, 323, 123, 454, 5321, 3136);
        //最小值
        Optional<Integer> min = list.stream().collect(Collectors.minBy(Integer::compare));
        System.out.println(min.get());
        //最大值
        Optional<Integer> max = list.stream().collect(Collectors.maxBy(Integer::compare));
        System.out.println(max.get());

        //求平均值 averagingInt  averagingDouble  averagingLong
        Double collect = list.stream().collect(Collectors.averagingInt(x -> x));
        System.out.println(collect);

        //总和
        Integer sum = list.stream().collect(Collectors.summingInt(x -> x));
        System.out.println(sum);

        //最大值、最小值、平均值、总和值、总数。
        DoubleSummaryStatistics statistics = list.stream().collect(Collectors.summarizingDouble(x -> x));
        statistics.getAverage();
        statistics.getCount();
        statistics.getMax();
        statistics.getMin();
        statistics.getSum();
    }

    /** Collectors.groupingBy()
     * personList中存放Person对象，根据Person对象的地址来分组
     * */
    @Test
    public void groupingByTest(){
        ArrayList<Person> personList = new ArrayList<>();

        personList.add(new Person("tom",20,"WH"));
        personList.add(new Person("cat",19,"WH"));
        personList.add(new Person("hive",30,"SH"));
        personList.add(new Person("spark",20,"SH"));
        personList.add(new Person("scala",40,"BJ"));
        personList.add(new Person("java",43,"BJ"));

        // ::关键字,下面两个Map是一样的
        Map<Object, List<Person>> mapList = personList.stream().collect(Collectors.groupingBy((x) -> x.getAddress()));
        Map<String, List<Person>> mapList2 = personList.stream().collect(Collectors.groupingBy(Person::getAddress));
        System.out.println(mapList2);

        List<Person> whPerson = mapList2.get("WH");
        System.out.println(whPerson);

        /** identity()是Function类的静态方法和x->x是一个意思*/
        List<String> list = Arrays.asList("wu", "sh", "bj");
        Map<String, List<String>> collect = list.stream().collect(Collectors.groupingBy(Function.identity()));
        Map<String, List<String>> collect1 = list.stream().collect(Collectors.groupingBy(x->x));
        System.out.println(collect);
    }

    /** Collectors.partitioningBy()
     * 将数据分为两部分，key为 true/false
     * */
    @Test
    public void partitionTest(){
        List<Integer> list = Arrays.asList(32, 12, 343, 44234, 2342);
        Map<Boolean, List<Integer>> collect = list.stream().collect(Collectors.partitioningBy(x -> x > 100));
        System.out.println(collect);
    }

    /**
     * Collectors.joining() 拼接字符串
     * 不指定分隔符的话，就全部连在一起
     * */
    @Test
    public void joinTest(){
        List<String> list = Arrays.asList("tom", "cat", "demo");
        String collect = list.stream().collect(Collectors.joining());
        String collect1 = list.stream().collect(Collectors.joining("|"));
        System.out.println(collect);
        System.out.println(collect1);
    }

    /**
     * Collectors.collectingAndThen()
     * 先执行Collect操作，再执行第二个参数的表达式。
     * 本例：先塞到集合，再得出集合的长度
     * */
    @Test
    public void andThenTest(){
        List<String> list = Arrays.asList("demo", "java", "scala");
        Integer collect = list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), x -> x.size()));
        System.out.println(collect);
    }
}
