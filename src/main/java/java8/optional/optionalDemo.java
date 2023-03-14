package java8.optional;

import com.google.common.base.Function;

import java.util.Optional;

/**
 * Created by anjunli on  2020/12/11
 *
 * Optional 类是一个可以为null的容器对象
 * of() 返回一个指定非空值的Optional
 * ofNullable  如果为非空，返回 Optional 描述的指定值，否则返回空的 Optional
 * isPresent() 如果值存在返回true,否则返回false
 * get() 如果在这个Optional中包含这个值，返回值，否则抛出异常：NoSuchElementException
 *
 **/
public class optionalDemo {
    public static void main(String[] args) {
        Person person1 = new Person("tom", 100);
        Person person2=null;

        Optional<Person> optional = Optional.of(person1);
        if (optional.isPresent()){
            Person person = optional.get();
            System.out.println("Person: "+person);
            //取到optional里面的对象，修改对象属性值后，再次调用get()方法，得到的对象是修改后的对象
            person.setAge(2000);
            System.out.println(optional.get());
        }

        /** map() 如果有值，则对其执行调用映射函数得到返回值。
        如果返回值不为 null，则创建包含映射返回值的Optional作为map方法返回值，否则返回空Optional。*/
        Optional<String> optionalMap = optional.map(x -> {
            String map = x.getName()+" "+x.getAge();
            return map;
        });
        System.out.println("optionalMap:"+optionalMap.get());

        //空指针异常
/*        Optional<Object> optional2 = Optional.of(null);
        if (optional2.isPresent()){
            System.out.println("not null");
        }else{
            System.out.println("is null");
        }*/

        //可以接受Null,返回一个空的optional; get() NoSuchElementException
        Optional<Person> optionalNull = Optional.ofNullable(person2);
        if(!optionalNull.isPresent()){
            Person personNull = optionalNull.get();
        }

    }
}
