// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ActiveUid.java

package com.upchina.activeuid;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * √ 标记用于本地测试，Edit configurations... ->Program arguments
 * */

public class ActiveUid {
    public static String date = "";
    public static String dbUrl = "";
    public static String dbUser = "";
    public static String dbPwd = "";
    public static String Channel="";

    public ActiveUid() {
    }

    public static void main(String args[]) throws Exception {
        date = args[0];
        dbUrl = args[1];
        dbUser = args[2];
        dbPwd = args[3];
        Channel=args[4];

        SparkConf sparkConf = new SparkConf();
//        sparkConf.setMaster("local[*]").set("spark.sql.warehouse.dir","D:\\sparkSQL");

        sparkConf.setExecutorEnv("HADOOP_USER_NAME", "hdfs");
        sparkConf.setAppName("Login_uid_" + date);
        System.setProperty("HADOOP_USER_NAME", "hdfs");
        SparkContext sparkContext = new SparkContext(sparkConf);
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkContext);
        javaSparkContext.setLogLevel("ERROR");
        SparkSession sparkSession = new SparkSession(sparkContext);
        System.out.println("Login_uid_"+date);

        String logDir = "/taf/warehouse/Base/HttpLogServer/" + date + "/Base.HttpLogServer_loginloginlogin_" + date + "_*";
//        String logDir = "spark-warehouse/" + "Base.HttpLogServer_loginloginlogin_" + date + "_*";
        ParseLoginLogFunction parseLoginLogFunction = new ParseLoginLogFunction();
        FilterLoginLogFunction filterLoginLogFunction = new FilterLoginLogFunction(Channel);
        JavaRDD<LoginRecord> javaRDD = javaSparkContext.textFile(logDir).map(parseLoginLogFunction).filter(filterLoginLogFunction);

        long count = javaRDD.count();
        System.out.println("javaRDD count: " + count);

        Dataset<Row> dataFrame = sparkSession.createDataFrame(javaRDD, com.upchina.activeuid.LoginRecord.class);
        dataFrame.createOrReplaceTempView("t_login_active");
        String sql = "select sUid,sTime from t_login_active ";
        Dataset<Row> dataset = sparkSession.sql(sql);
        List<Row> rowList = dataset.collectAsList();
        System.out.println("rowList size: " + rowList.size());

        String tableName="t_user_"+Channel;
        Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
/*        if (DbUtils.isTableExist(tableName,connection)){
            DbUtils.deleteTable(tableName,connection);
        }
        DbUtils.crateTable(connection,tableName);*/

        String sql2 = "";
        for (Row row : rowList) {
            sql2 = "replace into "+ tableName+" set u_id='" + row.getString(0) + "'" +
                    ", sTime='"+row.getString(1)+"'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeQuery();
        }
        connection.close();
        System.out.println("**********end**********");
        sparkSession.stop();
    }
}
