package db;

import db.utils.DataSourceUtils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

/**
 * @Description 获取数据库连接
 * @Date 2020/9/1  12:32
 **/
public class Demo1 {

    public static void main(String[] args) throws Exception {
        BasicDataSource ds = DataSourceUtils.createDataSource(
                "jdbc:mysql://localhost:3306/db_es_taf_stat_md?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false",
                "userName", "passwd");
        Connection connection = ds.getConnection();

        String tableName="taf_2020111001";
        String sql="select * from "+tableName +" limit 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Timestamp timestamp = resultSet.getTimestamp(1);
            System.out.println(timestamp);

            java.util.Date date1 = new java.util.Date(timestamp.getTime());
            System.out.println(date1);

            Date date = resultSet.getDate(2);
            System.out.println(date);
            java.util.Date date2 = new java.util.Date(date.getTime());
            System.out.println(date2);
        }

        ds.close();
        connection.close();
    }

}
/**
 * 格林威治时间
 * output
 *
 * 2020-11-10 01:05:08.0
 * Tue Nov 10 01:05:08 CST 2020
 * 2020-11-10
 * Tue Nov 10 00:00:00 CST 2020
 */
