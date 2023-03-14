package com.upchina

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.filter._
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}

/**
 * @Description RowFilter 行过滤器
 * @Date 2021/4/22  14:27
 **/
object HbaseFilterTest {
  def main(args: Array[String]): Unit = {
    val conf: Configuration = HBaseConfiguration.create()
    val connection: Connection = ConnectionFactory.createConnection(conf)
    val table: Table = connection.getTable(TableName.valueOf("testtable"))

    //创建一个过滤器，指定比较运算符和比较器，这里需要精确匹配。rowkey字典序小于等于row-22的
    val scan = new Scan()
    scan.addColumn(Bytes.toBytes("colfaml"),Bytes.toBytes("col-0"))
    val filter1 = new RowFilter(CompareFilter.CompareOp.LESS_OR_EQUAL, new BinaryComparator(Bytes.toBytes("row-22")))
    scan.setFilter(filter1)
    val scanner1: ResultScanner = table.getScanner(scan)
    println(scanner1)
    scanner1.close()

    //创建另一个过滤器，使用正则表达式来匹配行键  row-25 row-35...
    val filter2 = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(".*-.5"))
    scan.setFilter(filter2)
    val scanner2: ResultScanner = table.getScanner(scan)
    println(scanner2)
    scanner2.close()

    //创建第三个过滤器，使用子串匹配方法。 row-5 row-51...
    val filter3 = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator("-5"))
    scan.setFilter(filter3)
    val scanner3: ResultScanner = table.getScanner(scan)
    println(scanner3)
    scanner3.close()
  }

}
