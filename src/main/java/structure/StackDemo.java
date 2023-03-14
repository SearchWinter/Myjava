package structure;

import java.util.Stack;

/**
 * @ClassName StackDemo
 * @Description 栈
 * @Author Li Anjun
 * @Date 2020/7/2  15:32
 **/
public class StackDemo {
    public static void main(String[] args) {
        Stack stack=new Stack<Integer>();
        //添加元素
        stack.add(10);
        stack.push(100);
        stack.push(10);
        //向指定位置添加元素
        stack.add(0,20);

        //判断是否为空
//        System.out.println(stack.empty());

        //返回元素的个数
//        System.out.println(stack.size());

        //返回栈的第一个元素
//        System.out.println(stack.firstElement());
        //返回最后一个元素
//        System.out.println(stack.lastElement());

        //查看顶部的数据，但是不移除它   顶部也就是索引最大
//        System.out.println(stack.peek());

        //移除顶部的数据
//        System.out.println(stack.pop());

        //返回下标为1的元素  下表从0开始
//        System.out.println(stack.get(0));

        //返回第一次出现此元素的索引，如果不包含则返回-1
//        System.out.println(stack.indexOf(10));

        //移除指定位置的元素
/*        stack.remove(0);
        System.out.println(stack.size()+"*****"+stack.get(0));*/

        //返回索引为1的元素的哈希码
//        System.out.println(stack.get(1).hashCode());

        //返回元素的位置,找到一个就返回，就算有多个也只返回第一个
        System.out.println(stack.search(10));


    }
}
