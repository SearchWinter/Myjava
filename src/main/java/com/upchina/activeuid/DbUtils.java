package com.upchina.activeuid;

import java.sql.*;

/**
 * Created by anjunli on  2021/1/28
 **/
public class DbUtils {
    public static void crateTable(Connection connection, String tableName) {
        String sql = "CREATE TABLE "+tableName+" (\n" +
                "  `u_id` varchar(100) COLLATE utf8mb4_bin NOT NULL,\n" +
                "  `sTime` varchar(50) COLLATE utf8mb4_bin NOT NULL,\n" +
                "  PRIMARY KEY (`u_id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin\n";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public static void deleteTable(String tableName, Connection connection) throws SQLException {
        String sql = "drop table " + tableName;

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }
}
