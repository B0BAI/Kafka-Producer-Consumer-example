package io.b0b.ai;

import java.util.concurrent.ExecutionException;

import io.b0b.ai.pojo.MyObject;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import io.b0b.ai.constants.IKafkaConstants;
import io.b0b.ai.producer.ProducerCreator;

public class ProducerAPP {
    public static void main(String[] args) {
        runProducer();
    }

    private static void runProducer() {
        Producer<Long, MyObject> producer = ProducerCreator.createProducer();
        for (int index = 1; index < 100; index++) {
            MyObject co = new MyObject("NG1-" + index, "B0BS -" + index);

            final ProducerRecord<Long, MyObject> record = new ProducerRecord<>(IKafkaConstants.TOPIC_NAME, co);
            try {
                RecordMetadata metadata = producer.send(record).get();
                System.out.println(metadata.toString() + " Something though!");
            } catch (ExecutionException | InterruptedException e) {
                System.out.println("Error in sending record");
                System.out.println(e);
            }
        }
    }
}
