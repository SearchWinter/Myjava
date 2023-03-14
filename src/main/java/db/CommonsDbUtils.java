package db;

import db.utils.DataSourceUtils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by anjunli on  2020/12/1
 * commons-dbutils 相关方法
 **/
public class CommonsDbUtils {
    private static BasicDataSource ds = null;
    private static Connection connection=null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        ds = DataSourceUtils.createDataSource(
                "jdbc:mysql://*.*.*.*:3306/db_es_taf_stat_md?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false",
                "userName", "passwd");
        connection=ds.getConnection();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        ds.close();
        connection.close();
    }

    /**
     * update
     * 修改数据库中的数据，支持动态参数
     * */
    @Test
    public void updateTest() throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql ="insert into test(table_name,rows) values(?,?)";
        int result = runner.update(connection, sql, "updateDemo", 30);
        System.out.println("受到影响的记录条数："+result);
    }

    /**
     * MapListHandler
     * 将结果集中的数据转换为一个集合，集合中的数据为对应行转换的键值对，键为列名
     * */
    @Test
    public void test() throws SQLException {
        String sql="select * from taf_stat_test limit 10";
        QueryRunner runner = new QueryRunner();
        List<Map<String, Object>> result = runner.query(ds.getConnection(), sql, new MapListHandler());
//        System.out.println(result);
        System.out.println(result.size());
        System.out.println(result.get(0));

        //得到Object对象的原类型
        String stattime = result.get(0).get("stattime").getClass().getName();
        System.out.println(stattime);

        String f_date = result.get(0).get("f_date").getClass().getName();
        System.out.println(f_date);
    }
}
