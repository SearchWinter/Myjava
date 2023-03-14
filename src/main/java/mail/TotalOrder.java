package mail;

import java.text.NumberFormat;

/**
 * Created by anjunli on  2021/5/27
 **/
public class TotalOrder {
    private NumberFormat numberFormat=NumberFormat.getPercentInstance();
    //收入总计
    private int totalIncome;
    //下单数量
    private int orderNum;
    //支付数量
    private int payNum;
    //支付成功率
    private String payRate;

    public TotalOrder() {
    }

    public TotalOrder(int totalIncome, int orderNum, int payNum) {
        numberFormat.setMaximumFractionDigits(2);
        this.totalIncome = totalIncome;
        this.orderNum = orderNum;
        this.payNum = payNum;
        this.payRate = numberFormat.format(Double.valueOf(payNum)/Double.valueOf(orderNum));
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getPayNum() {
        return payNum;
    }

    public void setPayNum(int payNum) {
        this.payNum = payNum;
    }

    public String getPayRate() {
        return payRate;
    }

    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    @Override
    public String toString() {
        return "TotalOrder{" +
                "totalIncome=" + totalIncome +
                ", orderNum=" + orderNum +
                ", payNum=" + payNum +
                ", payRate=" + payRate +
                '}';
    }
}
