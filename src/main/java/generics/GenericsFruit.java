package generics;



/**
 * @ClassName GenericsFruit
 * @Description 泛型类里面可以定义泛型方法，新定义的泛型可以与泛型类里面的一样，也可以不一样。
 * @Author Li Anjun
 * @Date 2020/7/2  10:23
 **/
public class GenericsFruit {
    static class Fruit{
        @Override
        public String toString() {
            return "Fruit";
        }
    }
    static class Apple extends Fruit{
        @Override
        public String toString() {
            return "Apple";
        }
    }
    static class Person{
        @Override
        public String toString() {
            return "person";
        }
    }
    //泛型类
    static class GenericsTest<T>{
        public void show_1(T t){
            System.out.println(t.toString());
        }
        //泛型方法
        //泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中未声明泛型，编译器也能够正确识别方法中的泛型
        //使用泛型E，这种泛型E可以是任意类型，类型可以与T相同，也可以不同
        public <E> void show_2(E e){
            System.out.println(e.toString());
        }

        //在泛型类里面声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型
        public <T> void show_3(T t){
            System.out.println(t.toString());
        }
    }
    public static void main(String[] args) {
        Apple apple=new Apple();
        Person person=new Person();

        //apple是Fruit的子类，所以这里可以使用show_1(apple)
        GenericsTest<Fruit> genericsTest=new GenericsTest<Fruit>();
        genericsTest.show_1(apple);
        //因为泛型类型参数实参指定的是Fruit,而传入的实参类是Person
//        genericsTest.show_1(person);

        //这两种都可以成功
        genericsTest.show_2(apple);
        genericsTest.show_2(person);

        //这两种都可以成功
        genericsTest.show_3(apple);
        genericsTest.show_3(person);

    }
}
