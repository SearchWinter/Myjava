package structure.list;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @ClassName ArrayList
 * @Description TODO
 * @Author Li Anjun
 * @Date 2020/7/2  16:46
 **/
public class ArrayListDemo {
    public static void main(String[] args) {
        //ArrayList的三种构造方法
        //第一种：构造一个空的ArrayList<> 默认长度为10
        ArrayList<Integer> list = new ArrayList<>();
        //第二种：构造一个长度为20的ArrayList<>
        ArrayList<Integer> integers1 = new ArrayList<>(20);
        //第三种：构造包含指定集合元素的列表，按照集合的迭代器返回元素的顺序。
        Collection<Integer> arrlist=new ArrayList<Integer>();
        arrlist.add(10);
        arrlist.add(20);
        arrlist.add(30);
        arrlist.add(10);
        ArrayList<Integer> anotherlist = new ArrayList<>(arrlist);
        System.out.println(anotherlist);
        //添加元素
        list.add(1);
        list.add(2);
        //在指定位置添加元素
        list.add(0,3);
        System.out.println(list);

        //更改指定位置的元素
//        list.set(1,1000);

        //得到指定索引位置的元素
//        list.get(2);

        //将指定集合的元素添加进去
//        list.addAll(arrlist);
        //从指定位置将集合的元素添加进去
//        list.addAll(0,arrlist);


        //迭代器遍历
//      遍历ArrayList时，通过索引值遍历效率最高，for循环遍历次之，迭代器遍历最低。
        //第一种：迭代器遍历
      Iterator<Integer> listIterator = list.iterator();
        while(listIterator.hasNext()){
            Integer i = listIterator.next();
            //迭代器里面的元素==2，就执行remove操作，移除，并跳出循环
            if(i.equals(2)){
                listIterator.remove();
                break;
            }
            System.out.println(i);
        }
        //第二种：索引遍历
/*        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }*/
        //第三种：for-each
/*        for (Integer i:list){
            System.out.println(i);
        }*/

        //toArray() 返回的是object[]数组
/*        Object[] objects = list.toArray();
        //import java.util.Arrays
        System.out.println(Arrays.toString(objects));*/

        //返回指定元素的索引,第一次出现
//        System.out.println(list.indexOf(3));
        //最后一次出现的索引
//        System.out.println(list.lastIndexOf(2));

        //移除指定位置的元素
//        list.remove(1);

        //移除指定元素---当匹配到多个的时候，只移除最开始找到的这个元素
/*        list.add(4);
        list.add(2);
        list.remove(2);
        System.out.println(list);*/

        //清空所有的元素
//        list.clear();

        //验证元素是否存在  return boolean
//        System.out.println(list.contains(2000));

        //使用isEmpty()判断list是否为空
//        System.out.println(list.isEmpty());

        //使用sort()方法对list排序--默认从小到大，如果时字母的话就按照A-Z排序
//        Collections.sort(list);

        //排序
//        Collections.sort(list);
        Collections.sort(list,Collections.reverseOrder());
        System.out.println(list);

    }

    @Test
    public void testA(){
        ArrayList<Integer> list = new ArrayList<>(10);
        list.add(100);
        list.add(300);
        list.add(500);
        list.add(700);
        Collections.sort(list);
        System.out.println(list.get(0));
    }
}
