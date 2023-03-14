package generics.genericsinterface;

/**
 * @ClassName FruitGenerics
 * @Description 未传入泛型实参
 * @Author Li Anjun
 * @Date 2020/7/1  18:48
 **/
//未出入泛型实参时，需要将泛型的声明一起加到类中
//    public class FruitGenerics implements GenericsInter<T>{

public class FruitGenerics<T> implements GenericsInter<T>{
    public static void main(String[] args) {
        FruitGenerics<String> fruitGenerics=new FruitGenerics<>();
        fruitGenerics.display("generic interface demo");
    }
    @Override
    public void display(T key) {
        System.out.println(key);
    }

    @Override
    public T next() {
        return null;
    }
}
