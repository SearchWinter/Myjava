package reflect.classmethod;

import java.lang.reflect.Method;

/**
 * @ClassName MethodDmeo
 * @Description java.lang.reflect.Method方法案例
 * @Author Li Anjun
 * @Date 2020/7/10  17:11
 **/
public class MethodDmeo {
    public static void main(String[] args) throws Exception {
        //获取一个class对象
        Class<Tutorial> tutorialClass = Tutorial.class;
        //获取class对象对应类的实例
        Tutorial tutorial = tutorialClass.newInstance();

        //通过class对象获取方法
        Method fly = tutorialClass.getDeclaredMethod("fly", String.class, int.class);
        //invoke()  方法有多个参数的话，要在后面给实参
//        fly.invoke(tutorial,"鸽子",300);

        /** 布吉岛*/
//        System.out.println(fly.getDefaultValue());

        //getGenericReturnTyp类型
        ////        System.out.println(fly.getGenericReturnType());
        ////        System.out.println(fly.getReturne()  方法的返回值Type());

        //getParameterCount()
//        System.out.println(fly.getParameterCount());

        //getParameterTypes()       返回一个包含所有参数类型的数组
/*        Class<?>[] parameterTypes = fly.getParameterTypes();
        System.out.println(parameterTypes.length);
        for(Class parameter:parameterTypes){
            System.out.println(parameter);
        }*/

        //isDefault()
//        System.out.println(fly.isDefault());

//        System.out.println(fly.toString());
    }
}
