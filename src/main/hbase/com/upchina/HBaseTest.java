package com.upchina;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

/**
 * Created by anjunli on  2021/4/25
 **/
public class HBaseTest {
    public static void main(String[] args) {

    }

    private static void insertRecord(Table table, List<String> list) throws IOException {
        Put put = new Put(Bytes.toBytes("myrow-1"));
        put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("uid"), Bytes.toBytes(list.get(0)));
        put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("time"), Bytes.toBytes(list.get(1)));
        table.put(put);
    }
}
