import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by anjunli on  2021/1/15
 * org.apache.kafka.clients.consumer.ConsumerConfig
 **/
public class TestConsumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost.82:9092");
        properties.put("group.id", "app20200121");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "10");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        //单次poll()请求的数据条数，默认500
        properties.put("max.poll.records","1000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        TopicPartition topicPartition = new TopicPartition("taf-log", 0);
        ArrayList<TopicPartition> list = new ArrayList<>();
        list.add(topicPartition);
        consumer.assign(list);
//        consumer.subscribe(Arrays.asList("test"));
        //执行一次的话，时间可能不够，会没有数据，只消费一部分数据
        int count=1;
        while (count<10) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(5000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, value = %s", record.offset(), record.value() + "\n");
                System.out.println();
                count++;
            }
        }

        //自动触发提交offset
        //对于自动提交偏移量，如果auto_commit_interval_ms的值设置的过大，当消费者在自动提交偏移量之前异常退出，将导致kafka未提交偏移量，进而出现重复消费的问题，所以建议auto_commit_interval_ms的值越小越好。
//        consumer.close();
    }

    @Test
    public void testConsumer() {

    }
}
