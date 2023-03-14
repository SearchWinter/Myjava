package generics;


/**
 * @Author Li Anjun
 * @Description 泛型类
 * @Date 17:47 2020/7/1
 **/
public class Box<T> {
    private T t;
    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<Integer>();
        Box<String> stringBox = new Box<String>();

        integerBox.add(10);
        stringBox.add("rainbow");
//F
        Box<Integer> a=(Box) stringBox;
        System.out.println(integerBox.get());
        System.out.println(stringBox.get());
    }
    public void add(T t){
        this.t=t;
    }
    public T get(){
        return t;
    }
}
