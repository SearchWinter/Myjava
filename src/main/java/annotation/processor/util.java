package annotation.processor;

import java.lang.reflect.Field;

/**
 * @ClassName util
 * @Description 工具类
 * @Author Li Anjun
 * @Date 2020/7/10  10:25
 **/
public class util {

    //解析@FruitProvider注解，提取供应商信息
    public static void getFruitInfo(Class<?> clazz){
        String strFruitProvider="供应商信息：";
        //通过反射获取处理注解
        Field[] fields=clazz.getDeclaredFields();
        for(Field field:fields){
            //注解处理的地方
            if(field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
                strFruitProvider="供应商id:"+fruitProvider.id() +
                        "供应商名称："+fruitProvider.name()+
                        "供应商地址："+fruitProvider.address();
                System.out.println(strFruitProvider);
            }
        }
    }
    //解析@FruitNum注解，提取销售信息
    public static void getFruitNum(Class<?> clazz){
        String sell="销售信息：";
        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(FruitNum.class)){
                FruitNum fruitNum = field.getAnnotation(FruitNum.class);
                sell="水果的总数为："+(fruitNum.sum())+
                        "销售的数量为"+String.valueOf(fruitNum.sellNum());
                System.out.println(sell);
            }
        }
    }
}