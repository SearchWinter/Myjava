package com.upchina

import java.sql.{Connection, DriverManager}

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client.{ConnectionFactory, Put, Table}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName, client}
import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description mysql数据存储到HBase
 * @Date 2021/4/21  18:52
 **/
object MysqlToHbase {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("MysqlToHbase")

    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    val rdd = new JdbcRDD(
      sc,
      //匿名函数
      ()=>getConnection(),
      "select uid,app,plat,channel,timestamp from t_static_day",
      0,
      100000,
      10,
      result => (result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5))
    )

    rdd.foreachPartition(x=>{
      x.map(x=>{
        val conf : Configuration = HBaseConfiguration.create()
        val connection: client.Connection = ConnectionFactory.createConnection(conf)
        val table: Table = connection.getTable(TableName.valueOf("t_static_day"))

        val put = new Put(Bytes.toBytes(x._1))
        put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("app"),Bytes.toBytes(x._2))
        put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("plat"),Bytes.toBytes(x._3))
        put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("channel"),Bytes.toBytes(x._4))
        put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("timeStamp"),Bytes.toBytes(x._5))

        table.put(put)
        table.close()
      })
    })
    sc.stop()
  }

  def getConnection():Connection={
    Class.forName("com.mysql.jdbc.Mysql")
    val connection: Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_base_stat?userUnicode=yes&characterEncoding=utf-8", "root", "123456")
    connection
  }

}
