package mail;

/**
 * Created by anjunli on  2021/5/28
 **/
public class Order {
    //订单ID
    private String orderID;
    //用户名（优品）
    private String userName;
    //用户名（新浪）
    private String wbID;
    //下单渠道
    private String currch;
    //下单时间
    private String ctime;
    //支付时间
    private String otime;
    //产品ID
    private String producteID;
    //产品名
    private String productName;
    //期限
    private String totalDay;
    //支付类型
    private String payType;
    //支付金额
    private String payTotal;

    public Order() {
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWbID() {
        return wbID;
    }

    public void setWbID(String wbID) {
        this.wbID = wbID;
    }

    public String getCurrch() {
        return currch;
    }

    public void setCurrch(String currch) {
        this.currch = currch;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public String getProducteID() {
        return producteID;
    }

    public void setProducteID(String producteID) {
        this.producteID = producteID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(String totalDay) {
        this.totalDay = totalDay;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(String payTotal) {
        this.payTotal = payTotal;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", userName='" + userName + '\'' +
                ", wbID='" + wbID + '\'' +
                ", currch='" + currch + '\'' +
                ", ctime='" + ctime + '\'' +
                ", otime='" + otime + '\'' +
                ", producteID='" + producteID + '\'' +
                ", productName='" + productName + '\'' +
                ", totalDay='" + totalDay + '\'' +
                ", payType='" + payType + '\'' +
                ", payTotal='" + payTotal + '\'' +
                '}';
    }
}
