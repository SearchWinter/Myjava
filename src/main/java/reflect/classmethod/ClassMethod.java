package reflect.classmethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName Test
 * @Description class方法
 * @Author Li Anjun
 * @Date 2020/7/10  13:42
 **/
public class ClassMethod {
    public static void main(String[] args) throws Exception {
        //返回给定类名的类或接口的class对象
        Class<?> test = Class.forName("reflect.classmethod.ClassMethod");
        Class<?> interfaceDemo = Class.forName("interfacedemo.Demo01");

        //返回由这个class对象表示的实体(类、接口)的名称
//        System.out.println(test.getName());
//        System.out.println(interfaceDemo.getName());

        //返回这个类所在的包
//        System.out.println(test.getPackage());

        /** getDeclareFields()和getFields()的区别
         *  getFields()只能够获取到公有的属性,private protected修饰的属性不能够得到
         * */
        /** 通过反射得到属性和属性值*/
        Class<Tutorial> tutorialClass = Tutorial.class;
        Tutorial tutorial = new Tutorial();
/*
        Field[] fields = tutorialClass.getDeclaredFields();
        for (Field field : fields) {
            //打开私有访问，可以获取到"private"修饰的属性和方法
            field.setAccessible(true);
            System.out.println(field.getName()+field.get(tutorial));
        }

        Field[] fields1 = tutorialClass.getFields();
        for(Field field:fields1){
            System.out.println(field.getName()+field.get(tutorial));
        }
*/

        //只可以访问public和protected修饰的属性
        Field name = tutorialClass.getDeclaredField("id");
        System.out.println(name.get(tutorial));

        //只能访问public修饰的属性
/*        Field sum = tutorialClass.getField("id");
        System.out.println(sum.get(tutorial));*/

        /** 通过反射得到成员方法并运行*/
        //getMethod()  getDeclareMethod()
/*
        Method display = tutorialClass.getMethod("display");
        display.invoke(tutorial);
*/

        Method show = tutorialClass.getDeclaredMethod("show");
        show.setAccessible(true);
        show.invoke(tutorial);

        //创建一个class对象对应类的实例
        //newInstance()
        Tutorial tutorial1 = tutorialClass.newInstance();
        //constructor
        Constructor<Tutorial> constructor = tutorialClass.getConstructor(int.class, String.class, int.class);
        Tutorial person = constructor.newInstance(100, "person", 2020);
    }
}
