package structure.map.hashmapdemo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @ClassName HashMapInitialCapacity
 * @Description 查看hashmap的容量(capacity)和阙值=容量*负载因子（DEFAULT_LOAD_FACTOR）
 * @Author Li Anjun
 * @Date 2020/7/7  15:01
 **/
public class HashMapCapacity {
    public static void main(String[] args) throws Exception{
        //不给定initialCapacity时，默认初始容量为16
        //给定inticalCapacity时，就会取大于等于参数的最小2次幂
        HashMap<Integer, String> hashMap = new HashMap<>(10);
        //获取hashMap整个类
        Class<? extends HashMap> mapType = hashMap.getClass();

/**
        就是利用反射的机制，获取HashMap的非public方法capacity()和非public属性threshold。
*/

        //获取指定属性 阙值
            Field threshold = mapType.getDeclaredField("threshold");
        //将目标属性设置为可以访问
        threshold.setAccessible(true);

        //获取指定方法，因为HashMap没有容量这个属性，但是capacity方法会返回容量值
        Method capacity = mapType.getDeclaredMethod("capacity");
        //将目标方法设置为可以访问
        capacity.setAccessible(true);

        /**
         * 容量：是取大于等于参数的最小2次幂，如给定10，那么容量就是2的4次方16，通过源码中的tableSizeFor()实现  | 是按位或，>>>无符号右移
         * 阈值：在初始化的时候，阈值是等于容量的；当放入第一个元素后，重新计算阈值，新的阈值=容量*负载因子。
         */
        System.out.println("容量："+capacity.invoke(hashMap)+"   阙值"+threshold.get(hashMap));

        //添加元素
        hashMap.put(10,"cat");
        System.out.println("容量："+capacity.invoke(hashMap)+"   阙值"+threshold.get(hashMap));


    }
}
