package db;


import db.utils.DataSourceUtils;
import db.utils.Utils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;
import java.sql.*;

/**
 * @author up-517
 * @date 2020/07/02
 */
public class TestAll {
    private static BasicDataSource ds = null;

    public static void setUpBeforeClass() throws Exception {
        ds = DataSourceUtils.createDataSource(
                "jdbc:mysql://x.x.x.x:3306/db_es_taf_stat_md?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8",
                "userName", "passwd");
    }

    /**
     * @throws java.lang.Exception
     */
    public static void tearDownAfterClass() throws Exception {
        ds.close();
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        System.out.println("setUp");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");
    }


    @org.junit.Test
    public void testRead() {
        System.out.println("testRead");
    }

    /** ResultSet的三种取时间方法
     * getDate()    只返回日期部分
     * getTime()    只返回时间部分
     * getTimestamp()   返回时间和日期
     * */
    @Test
    public void testGetDate() throws SQLException {
        String tableName="taf_stat_test";
        //limit 前面有空格，不然和tableName连在一起
        String sql="select * from  "+tableName +" limit 1";

        Connection connection = ds.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            try{
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    Date date = resultSet.getDate(1);
                    System.out.println(date);

                    Time time = resultSet.getTime(1);
                    System.out.println(time);

                    Timestamp timestamp = resultSet.getTimestamp(1);
                    System.out.println(timestamp);
                }
            }finally {
                statement.close();
            }
        }finally {
            connection.close();
        }
    }

    /** output
     * 2020-11-24
     * 15:05:08
     * 2020-11-24 15:05:08.0
     */

    /** 测试DriverManger.getConnection */
    @Test
    public void testGetConnection() throws SQLException, ClassNotFoundException {
        String dbUrl="jdbc:mysql://localhost:3306/db_es_taf_trace_log";
        String dbUser="userName";
        String dbPwd="passwd";
        Connection connection = Utils.getConnection(dbUrl, dbUser, dbPwd);

        connection.close();
    }

    /** float字段插入报错
     * java.sql.SQLException: Data truncated for column 'click_person_proportion' at row 1
     * 插入语句中有NaN(Not a Number) 查看代码
     * */
    @Test
    public void testFloat() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_base_stat?useUnicode=yes&characterEncoding=utf8", "userName1", "passwd");

        String sql="REPLACE INTO t_UPteachers_ios_less_new (day_time,teacher,TYPE ,rem_contents_sum ,rem_contents_distict_sum ,rem_person,click_times,click_person, click_rate,length_reading,click_person_proportion,click_count_proportion)\n" +
                "        VALUES ('20210326','金桂锦囊','all','52','1','40','0','0','0','0','NaN','NaN')";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        connection.close();
    }


    @Test
    public void testConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_base_stat?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8", "userName1", "passwd");
        connection.close();
    }
}
