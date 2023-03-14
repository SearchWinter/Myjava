package generics.genericsinterface;

/**
 * @ClassName GoodsGenerics
 * @Description 实现泛型接口的类，传入泛型实参时,需要将所有使用泛型的地方替换为传入实参的类型
 * @Author Li Anjun
 * @Date 2020/7/1  18:54
 **/
public class GoodsGenerics implements GenericsInter<String> {
    @Override
    public void display(String key) {
        System.out.println(key);
    }

    @Override
    public String next() {
        return null;
    }
}
