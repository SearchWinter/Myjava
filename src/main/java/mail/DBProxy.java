package mail;

import com.google.inject.internal.util.$SourceProvider;

import java.security.acl.Group;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by anjunli on  2021/5/27
 **/
public class DBProxy {
    private Connection connection;
    private DBProxy instance;

    public TotalOrder getTotalOrder(String beginTime,String endTime){
        int totalIncome=0;
        int payNum=0;
        int orderNum=0;
        String sql="select floor(sum(paytotal)/100) as paytotal,count(*) as count from db_base_stat.t_new_sync_order where ctime between ? and ? and currch=27102 and status in ('220','80') and paytotal >=10";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,beginTime);
            pstmt.setString(2,endTime);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                totalIncome= resultSet.getInt(1);
                payNum = resultSet.getInt(2);
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql2="select count(*) as count from db_base_stat.t_new_sync_order where ctime between ? and ? and currch=27102";
        try {
            PreparedStatement pstmt2 = connection.prepareStatement(sql2);
            pstmt2.setString(1,beginTime);
            pstmt2.setString(2,endTime);
            ResultSet resultSet = pstmt2.executeQuery();
            while(resultSet.next()){
                orderNum = resultSet.getInt(1);
            }
            pstmt2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new TotalOrder(totalIncome,orderNum,payNum);
    }

    /** 按产品ID分组*/
    public List<GroupOrder> getGroupOrder(String beginTime,String endTime){
        HashMap<String, GroupOrder> map = new HashMap<>();

        //支付成功
        String sql="SELECT CONCAT_WS(\"\",t2.name,t3.name) AS NAME,t1.* FROM \n" +
                "(\n" +
                "SELECT productid,FLOOR(SUM(paytotal)/100) AS paytotal,COUNT(*) AS COUNT FROM db_base_stat.`t_new_sync_order`\n" +
                "WHERE ctime BETWEEN "+beginTime+" AND "+endTime+"\n" +
                "AND currch =27102\n" +
                "AND STATUS IN (\"220\",\"80\")\n" +
                "AND paytotal>=10\n" +
                "GROUP BY productid\n" +
                ") t1\n" +
                "LEFT JOIN db_base_stat.t_product_package t2 ON t2.id=t1.productid\n" +
                "LEFT JOIN db_base_stat.`t_product` t3 ON t3.id=t1.productid";

        //订单
        String sql2="SELECT CONCAT_WS(\"\",t2.name,t3.name) AS NAME,t1.* FROM \n" +
                "(\n" +
                "SELECT productid,COUNT(*) AS COUNT FROM db_base_stat.`t_new_sync_order`\n" +
                "WHERE ctime BETWEEN "+beginTime+" AND "+ endTime+" " +
                "AND currch =27102\n" +
                "GROUP BY productid\n" +
                ")t1 \n" +
                " LEFT JOIN db_base_stat.`t_product`t2 ON t2.id=t1.productid\n" +
                " LEFT JOIN db_base_stat.`t_product_package` t3 ON t3.id=t1.productid";

        try {
            PreparedStatement pstmt = getConnection().prepareStatement(sql2);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                GroupOrder groupOrder1=new GroupOrder();
                groupOrder1.setProductName(resultSet.getString(1));
                groupOrder1.setProductID(resultSet.getString(2));
                groupOrder1.setOrderCount(resultSet.getInt(3));
                map.put(groupOrder1.getProductID(),groupOrder1);
            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<GroupOrder> list=new ArrayList<>(20);
        try {
            PreparedStatement pstmt2 = getConnection().prepareStatement(sql);
            ResultSet resultSet = pstmt2.executeQuery();
            while(resultSet.next()){
                GroupOrder groupOrder = map.get(resultSet.getString(2));
                Integer.parseInt(resultSet.getString(3));
                groupOrder.setPayTotal(resultSet.getInt(3));
                groupOrder.setPayCount(Integer.parseInt(resultSet.getString(4)));
                list.add(groupOrder);
            }
            resultSet.close();
            pstmt2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Order> getOrders(String beginTime,String endTime){
        List<Order> list = new ArrayList<Order>(50);
        String sql="SELECT t1.orderid,t1.username,t5.opid,t1.currch,t1.ctime,t1.otime,t1.productid,CONCAT_WS('|',t2.name,t3.name) AS NAME,t1.totaldays,t6.payName,t1.paytotal FROM\n" +
                "(\n" +
                "SELECT orderid,username,currch,FROM_UNIXTIME(ctime/1000,\"%Y-%m-%d %H:%i:%s\") AS ctime,productid,totaldays,webpayid,FLOOR(paytotal/100) AS paytotal,\n" +
                "FROM_UNIXTIME(otime/1000,\"%Y-%m-%d %H:%i:%s\") AS otime\n" +
                " FROM db_base_stat.`t_new_sync_order`\n" +
                "WHERE ctime BETWEEN "+beginTime+" AND "+endTime+"\n" +
                "AND currch =27102\n" +
                "AND STATUS IN (\"220\",\"80\")\n" +
                "AND paytotal>=10\n" +
                ") t1\n" +
                "LEFT JOIN db_base_stat.`t_product`t2 ON t2.id=t1.productid\n" +
                "LEFT JOIN db_base_stat.`t_product_package` t3 ON t3.id=t1.productid\n" +
                "LEFT JOIN db_base_stat.`t_new_sync_webpay_info` t4 ON t4.webpayid=t1.webpayid\n" +
                "LEFT JOIN db_base_stat.t_user_info t5 ON t1.username=t5.username\n" +
                "LEFT JOIN liujuan.t_pay_name t6 ON t4.payType=t6.payType";
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                Order order = new Order();
                order.setOrderID(resultSet.getString(1));
                order.setUserName(resultSet.getString(2));
                order.setWbID(resultSet.getString(3));
                order.setCurrch(resultSet.getString(4));
                order.setCtime(resultSet.getString(5));
                order.setOtime(resultSet.getString(6));
                order.setProducteID(resultSet.getString(7));
                order.setProductName(resultSet.getString(8));
                order.setTotalDay(resultSet.getString(9));
                order.setPayType(resultSet.getString(10));
                order.setPayTotal(resultSet.getString(11));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public DBProxy() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost1:3306/liujuan?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8", "userName", "passwd");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public DBProxy getInstance() {
        return instance;
    }

    public void setInstance(DBProxy instance) {
        this.instance = instance;
    }
}
