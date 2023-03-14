package structure.list;

import org.apache.calcite.runtime.Like;
import org.junit.Test;

import java.util.*;

/**
 * @Description LinkedList https://docs.oracle.com/javase/8/docs/api/
 *
 * @Date 2020/7/3  15:01
 **/
public class LinkedListDemo {
    public static void main(String[] args) {
        //构造方法
        //第一种：直接构造
        LinkedList<Integer> Linkedlist = new LinkedList<>();
        //第二种：
        /*Collection<Integer> collections = new LinkedList<Integer>();
        collections.add(10);
        collections.add(20);
        collections.add(30);
        LinkedList<Integer> integers = new LinkedList<>(collections);
        System.out.println(integers);*/

        //toArray()方法
        Linkedlist.add(20);
        Linkedlist.add(10);
        Linkedlist.add(30);
        Linkedlist.add(22);
        System.out.println("起始："+Linkedlist);
        /*Object[] objects = Linkedlist.toArray();
        System.out.println(Arrays.toString(objects));*/

        /** addFirst()  在第一个位置添加元素*/
//        Linkedlist.addFirst(1000);

        /** addLast()   在最后一个位置添加元素*/
//        Linkedlist.addLast(2000);

        /** clear()     清空所有的元素*/
//        Linkedlist.clear();

        /** clone()     克隆链表*/
        LinkedList<Object> list = new LinkedList<>();
        list = (LinkedList<Object>) Linkedlist.clone();

        /** contains    验证元素在链表中是否存在*/
//        System.out.println(Linkedlist.contains(null));

        /** descendingIterator      得到一个反序的迭代器 它自己的方法*/
        Iterator<Integer> iterator = Linkedlist.descendingIterator();
/*
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
*/

        /** size()        得到链表里面元素的个数*/
//        Linkedlist.size();


        /** 第一种：自定义一个ComparatorNum比较器 升序，里面的元素不能够有Null*/
        Collections.sort(Linkedlist,new ComparatorNum() );
        System.out.println("自定义比较器："+Linkedlist);

        /** 第二种：使用Collections里面的方法*/
        //降序
//       Collections.sort(Linkedlist,Collections.reverseOrder());
        //升序
//        Collections.sort(Linkedlist);

        /** get()   获取指定位置的值
         *  set()   给指定位置重新赋值
         *  remove()    移除指定位置的值
         * 去除链表里面所有的空值*/
/*        Linkedlist.add(null);
        for (int i=0;i<Linkedlist.size();i++){
            if (Linkedlist.get(i)==null){
                Linkedlist.set(i,-1);
            }
        }*/

        //统计链表里面有多少为空的值
/*        Linkedlist.add(null);
        int sum =0;
        for (int i=0;i<Linkedlist.size();i++){
            if (Linkedlist.get(i)==null){
                //添加此句表示移除所有为空的值
//                Linkedlist.remove(i);
                sum++;
            }
        }
        System.out.println(sum);*/

        /************** Queue operations  ********/

        /** peek()  检索列表的第一个元素*/
//        System.out.println(Linkedlist.peek());

        /** peekFirst()  peekLast()  检索列表的第一/最后一个元素
         * 如果列表为空的话，就返回null*/
/*        LinkedList<Integer> list1=new LinkedList<>();
        System.out.println(list1.peekFirst());
        System.out.println(list1.peekLast());*/

        /** element()   返回列表的第一元素
         *  如果列表为空的话，抛出 NoSuchElementException
         * */
/*        System.out.println(Linkedlist.element());
        LinkedList<Integer> listElement=new LinkedList<>();
        System.out.println(listElement.element());*/

        /** poll()  检索并移除列表的第一个元素*/
/*        System.out.println("poll(): "+Linkedlist.poll());
        System.out.println("poll()后："+Linkedlist);*/

        /** pollFirst() pollLast() 检索并移除列表的第一/最后个元素
         * 如果列表为空的话，就返回null*/
/*        System.out.println(Linkedlist.pollFirst());
        System.out.println(Linkedlist.pollLast());*/

        /** pop()   删除并返回该列表的第一个元素。*/
        System.out.println(Linkedlist.pop());

        System.out.println("end: "+Linkedlist);
    }

    static class ComparatorNum implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    /** LinkedList实现了Queue的方法,所以可以当做队列使用
     * 可以存放重复值，重新了toString()
     * */
    @Test
    public void queue(){
        Queue<Integer> queue=new LinkedList<>();
        queue.add(10);
        queue.add(10);
        queue.add(20);
        System.out.println(queue);
    }
}
