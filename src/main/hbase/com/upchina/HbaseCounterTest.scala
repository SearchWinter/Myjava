package com.upchina

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Table}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}


/**
 * @Description TODO
 * @Date 2021/4/22  15:51
 **/
object HbaseCounterTest {
  def main(args: Array[String]): Unit = {
    val conf: Configuration = HBaseConfiguration.create()
    val connection: Connection = ConnectionFactory.createConnection(conf)
    val table: Table = connection.getTable(TableName.valueOf("counters"))

    /** 单计数器自增方法 */
    //和使用Hbase shell incr 命令一样
    val cnt1: Long = table.incrementColumnValue(Bytes.toBytes("20200101"), Bytes.toBytes("daily"), Bytes.toBytes("hits"), 1)
    val cnt2: Long = table.incrementColumnValue(Bytes.toBytes("20200101"), Bytes.toBytes("daily"), Bytes.toBytes("hits"), 1)
    val cnt3: Long = table.incrementColumnValue(Bytes.toBytes("20200101"), Bytes.toBytes("daily"), Bytes.toBytes("hits"), 0)
    val cnt4: Long = table.incrementColumnValue(Bytes.toBytes("20200101"), Bytes.toBytes("daily"), Bytes.toBytes("hits"), -1)

    //1、计数器值加1。
    //2、给计数器值加1。
    //3、得到计数器当前值，不做自增操作。
    //4、计数器值减1。

    /** 多计数器
     * 需要一个Increment实例，
     * */


  }

}
