package generics;

/**
 * @ClassName Demo1
 * @Description 泛型类
 * @Author Li Anjun
 * @Date 2020/7/1  18:13
 **/
//T可以是任意标识，常见的如T E K V
    //泛型的类型参数只能是类类型，不能是简单类型。如：

public class Demo1<T> {
    private T key;

    //构造方法形参key的类型也为T
    public Demo1(T key) {
        this.key = key;
    }

    //泛型方法getkey的返回值类型为T
    //这只是一个普通的成员方法，只不过他的返回值是已经声明过的类型
    //所以在这个方法里面才能使用T类型
    public T getKey(){
        return key;
    }
/*   未在类中声明E，用E做形参和返回值类型时会报错。编译器无法识别
     public E getEkey(E key){
        return key;
    }*/
}