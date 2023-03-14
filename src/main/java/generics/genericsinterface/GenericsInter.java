package generics.genericsinterface;

/**
 * @ClassName GenericsInter
 * @Description 泛型接口
 * @Author Li Anjun
 * @Date 2020/7/1  18:46
 **/
public interface GenericsInter<T>  {
    public abstract T next();
    public abstract void display(T key);
}
