package casting;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Date 2020/6/30  10:42
 **/
public class ShopCar {
    private List<Electronics> mlist=new ArrayList<Electronics>();

    //往购物车里面添加商品
    public void add(Electronics electronics){
        mlist.add(electronics);
    }
    //获取购物车里商品的数量
    public int getSize(){
        return mlist.size();
    }
    //从购物车里获取相应的商品
    public Electronics getListItem(int position){
        return mlist.get(position);
    }
}
