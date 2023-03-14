package mail;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by anjunli on  2021/5/28
 **/
public class Test {
    @org.junit.Test
    public void test(){
        DBProxy dbProxy = new DBProxy();
        TotalOrder totalOrder = dbProxy.getTotalOrder(String.valueOf(1621958400000L), String.valueOf(1622044799000L));
        System.out.println(totalOrder);
    }


    @org.junit.Test
    public void test2(){
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);
        int a=7;
        int b=30;
        System.out.println(numberFormat.format(Double.valueOf(a)/Double.valueOf(b)));
    }


    @org.junit.Test
    public void test3(){
        DBProxy dbProxy = new DBProxy();
        List<GroupOrder> groupOrder = dbProxy.getGroupOrder(String.valueOf(1621958400000L), String.valueOf(1622044799000L));
        System.out.println(groupOrder);
    }
}
