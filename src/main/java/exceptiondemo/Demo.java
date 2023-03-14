package exceptiondemo;

import org.junit.Test;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * @ClassName Demo
 * @Description  finally不一定会被执行
 * @Author Li Anjun
 * @Date 2020/7/8  15:45
 **/
public class Demo {
    public static void main(String[] args) {
        //在try块之前就有异常抛出
        int[] arr = new int[-1];
        try{
            int i=100/0;
            System.out.println(i);
        }catch(Exception e){
            System.out.println(1);

            //退出当前正在运行的程序
//            System.exit(0);
            throw new java.lang.RuntimeException();
        }finally{
            System.out.println(2);
        }
    }

    /** 捕获异常后将异常打印出来，程序继续运行*/
    @Test
    public void testException(){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Integer[] arr={10,20,0,20,25};

        for (Integer integer:arr){
            try{
                System.out.println(100/integer);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("输出："+arr);


    }
}
