package structure;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by anjunli on  2020/12/17
 **/
public class IteratorTest {

    /**边界溢出示例
     * next()方法每调用一次都会指向下一个元素，下一个元素不存在时，边界溢出
     * 一个循环里，只能用一次next方法。可以使用中间变量保存这个值，再用
     * */
    @Test
    public void test1(){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(2300);
        list.add(2200);
        list.add(8232);
        Iterator<Integer> iterator = list.iterator();
        //NoSuchElementException  不会输出2200，它在第一次while循环，if判断里面就已经用了
        while(iterator.hasNext()){
            System.out.println(iterator.next());
            if(iterator.next()==2200){
                System.out.println("demo");
            }
        }
        //正确用法
        while(iterator.hasNext()){
            Integer integer = iterator.next();
            if(integer==2200){
                continue;
            }
            System.out.println(integer);
        }
    }

    /** LinkedList自己的方法
     * descendingIterator() 逆序的迭代器
     * */
    @Test
    public void test2(){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1213);
        list.add(233);
        list.add(3424);
        Iterator<Integer> iterator = list.descendingIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void removeTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1213);
        list.add(233);
        list.add(3424);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer i = iterator.next();
            if(i==1213){
                iterator.remove();
            }
        }
        System.out.println(list);
    }
}
