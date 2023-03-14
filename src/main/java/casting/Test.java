package casting;

/**
 * @Description 测试方法
 * @Date 2020/6/30  10:47
 **/
public class Test {
    public static void main(String[] args) {
        //添加进购物车
        ShopCar shopCar=new ShopCar();
        shopCar.add(new ThinkPad());
        shopCar.add(new Mouse());
        shopCar.add(new Keyboard());

        //测试购物车的方法
        System.out.println(shopCar.getSize());
        //向下转型
        ThinkPad thinkPad=(ThinkPad) shopCar.getListItem(0);
        thinkPad.boot();
        thinkPad.program();
    }
}
