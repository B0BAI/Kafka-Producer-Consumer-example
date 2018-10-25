package io.b0b.ai;

import io.b0b.ai.constants.IKafkaConstants;
import io.b0b.ai.pojo.MyObject;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;

public class ProducerThread implements Runnable {

    private Producer<Long, MyObject> producer;
    private int loopsCount;
    private String info;
    private int loopStart;

    ProducerThread(Producer<Long, MyObject> producer, int loopStart, int loopsCount, String info) {
        this.producer = producer;
        this.loopsCount = loopsCount;
        this.info = info;
        this.loopStart = loopStart;
    }

    @Override
    public void run() {
        for (int index = this.loopStart; index < this.loopsCount; index++) {
            MyObject myObject = new MyObject("NG1-" + index, this.info);
            final ProducerRecord<Long, MyObject> record = new ProducerRecord<>(IKafkaConstants.TOPIC_NAME, myObject);
            try {
                RecordMetadata metadata = producer.send(record).get();
                System.out.println(metadata.toString() + " Something though!");
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error in sending record");
                System.out.println(e);
            }
        }
    }
}
