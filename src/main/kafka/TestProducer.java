import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.util.Properties;

/**  Properties中的参数可以在org.apache.kafka.clients.producer.ProducerConfig中查看具体含义 */
public class TestProducer {
    public static String TOPIC_NAME="demo";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost.82:9092,localhost.83:9092");
        //指定了必须有多少个分区副本接收到了消息，生产者才会认为消息是发送成功的
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 5; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(TOPIC_NAME, String.valueOf(i), "spark to es demo " + i);
            producer.send(producerRecord);
        }
        producer.flush();
    }
}
