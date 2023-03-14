package annotation.processor;

/**
 * @ClassName Apple
 * @Description 使用注解
 * @Author Li Anjun
 * @Date 2020/7/9  18:56
 **/
public class Apple {
    @FruitProvider(id=1,name="红富士",address="陕西省西安市")
    private String appleProvider;

    @FruitNum()
    private int num;

    @FruitColor(fruitColor = FruitColor.Color.GREEN)
    private String color;

}
