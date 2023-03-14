package utils;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartitionInfo;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by anjunli on  2021/3/2
 * 获取kafka中某个topic所有partition的offset范围
 **/
public class FetchOffsetRange {
    public static void main(String[] args) {
        System.out.println(args[0]);
    }
}
