package db.utils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 数据库操作中常用的方法
 * @Date 2020/9/1  12:35
 **/
public class Utils {

    /** 使用Java自带的工具得到connection*/
    public static Connection getConnection(String dbUrl,String dbUser,String dbPwd) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        return connection;
    }

    /**
     * 判断表是否存在
     */
    public static Boolean isTableExist(String sTableName, Connection connection) throws Exception {
        //得到数据库元数据
        DatabaseMetaData dbms = connection.getMetaData();
        ResultSet rs = dbms.getTables(null, null, sTableName, new String[]{"TABLE"});
        while (rs.next()) {
            if (rs.getString("TABLE_NAME").equals(sTableName)) {
                return true;
            }
            return false;
        }

        return false;
    }


    /**
     * 获得指定数据库中的所有表名
     */
    public static List<String> getTableName(String dbName, DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        //获得元数据
        DatabaseMetaData metaData = connection.getMetaData();
        //获得表信息
        ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});

        List<String> list = new ArrayList<>();
        while (tables.next()) {
            //获得表名
            String table_name = tables.getString("TABLE_NAME");
            list.add(table_name);
        }

        connection.close();
        tables.close();
        return list;
    }

    //创建表
    public static void createTable(String sTableName,Connection connection) throws Exception {
        if (isTableExist(sTableName,connection)) {
            return;
        }

        String sql = "CREATE TABLE " + sTableName + " (" + "id int auto_increment primary key,"
                + "day_time varchar(64) not null," + "plat varchar(64) default \"\" ," + "app varchar(32) not null,"
                + "version varchar(32) default \"\"," + "channel varchar(64) default \"\","
                + "uid_type varchar(64) default \"\"," + "total_users int default 0,"
                + "unique `index_static`(day_time, plat, app, version,channel, uid_type),"
                + "last_user varchar(32) NOT NULL,"
                + "update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
                + ")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.executeUpdate();
    }

    /** 删除之前日志文件
     *  executeUpdate执行的是没有返回结果集的语句：insert delete update 或者创建语句
     *  executeUpdate 方法返回的是被更新条数，如果是删除语句则是被删除的条数
     * */
    public static int deleteBeforeLogFilePosition(String date, DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql="delete from t_log_position where log_file_name like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,date);

        int i = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
        return i;

    }
}
