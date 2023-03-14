import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;

import org.apache.thrift.TException;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by anjunli on  2021/8/16
 * java操作hive数据
 * https://hive.apache.org/javadocs/r3.1.2/api/index.html
 **/
public class DropTable {
    public static void main(String[] args) throws ParseException, TException {
        //CDH 操作->下载客户端配置
        HiveConf hiveConf = new HiveConf();
        hiveConf.set("hive.metastore.uris", "thrift://master9101:9083");
        HiveMetaStoreClient hiveClient = new HiveMetaStoreClient(hiveConf);

        //获取所有的数据库
        List<String> allDatabases = hiveClient.getAllDatabases();
        System.out.println(allDatabases);
        Table table = hiveClient.getTable("taflog", "push_connectserver_push_req");

        //获取分区
/*        Partition partition = hiveClient.getPartition("taflog", "push_connectserver_push_req", "yyyymmdd=20200101");
        System.out.println(partition);*/

        //获取指定表所有的分区名
/*
        List<String> strings = hiveClient.listPartitionNames("taflog", "push_connectserver_push_req", (short) 35);
        System.out.println("Size: "+strings.size());
        for (String str : strings) {
            System.out.println(str);
        }
*/

        //删除指定分区
/*        List<String> allDay = getAllDay("20190422", "20201231");
        for(String day:allDay) {
            String part_Name="yyyymmdd="+day;
            System.out.println("Delete partition: "+part_Name);
            try {
                hiveClient.dropPartition("taflog", "base_appstat_eventstream", part_Name, true);
            } catch (TException e) {
                e.printStackTrace();
            }
        }*/
    }

    @Test
    public void getPartName() throws TException {
        HiveConf hiveConf = new HiveConf();
        hiveConf.set("hive.metastore.uris", "thrift://master9101:9083");
        HiveMetaStoreClient hiveClient = new HiveMetaStoreClient(hiveConf);
        List<String> strings = hiveClient.listPartitionNames("taflog", "base_login_record_text_partition", (short) 35);
        for(String str:strings){
            System.out.println(str);
        }
    }


    public static List<String> getAllDay(String beginDay,String endDay) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        List<String> days = new ArrayList<>();
        days.add(beginDay);

        Date beginDate = dateFormat.parse(beginDay);
        Date endDate = dateFormat.parse(endDay);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);

        while(calendar.getTimeInMillis()<endDate.getTime()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            String nextDay = dateFormat.format(calendar.getTime());
            days.add(nextDay);
        }
        return days;
    }

}
