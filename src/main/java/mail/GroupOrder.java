package mail;

/**
 * Created by anjunli on  2021/5/28
 **/
public class GroupOrder {
    private String productName;
    private String productID;
    //收入合计
    private int payTotal;
    //下单数量
    private int orderCount;
    //支付数量
    private int payCount;
    //支付成功率
    private String paySucRate ;
    //总收入占比
    private String payRate;
    //总支付数占比
    private String payCountRate;

    public GroupOrder() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(int payTotal) {
        this.payTotal = payTotal;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getPayCount() {
        return payCount;
    }

    public void setPayCount(int payCount) {
        this.payCount = payCount;
    }

    public String getPaySucRate() {
        return paySucRate;
    }

    public void setPaySucRate(String paySucRate) {
        this.paySucRate = paySucRate;
    }

    public String getPayRate() {
        return payRate;
    }

    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    public String getPayCountRate() {
        return payCountRate;
    }

    public void setPayCountRate(String payCountRate) {
        this.payCountRate = payCountRate;
    }

    @Override
    public String toString() {
        return "GroupOrder{" +
                "productName='" + productName + '\'' +
                ", productID='" + productID + '\'' +
                ", payTotal=" + payTotal +
                ", orderCount=" + orderCount +
                ", payCount=" + payCount +
                ", paySucRate='" + paySucRate + '\'' +
                ", payRate='" + payRate + '\'' +
                ", payCountRate='" + payCountRate + '\'' +
                '}';
    }
}
