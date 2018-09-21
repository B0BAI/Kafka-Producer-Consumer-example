package io.b0b.ai;

import io.b0b.ai.constants.IKafkaConstants;
import io.b0b.ai.pojo.MyObject;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public class ConsumerThread implements Runnable {

    private Consumer<Long, MyObject> consumer;
    private String info;

    ConsumerThread(Consumer<Long, MyObject> consumer, String info) {
        this.consumer = consumer;
        this.info = info;
    }

    @Override
    public void run() {

        int noMessageToFetch = 0;

        while (true) {
            final ConsumerRecords<Long, MyObject> consumerRecords = consumer.poll(Long.MAX_VALUE);
            if (consumerRecords.count() == 0) {
                noMessageToFetch++;
                if (noMessageToFetch > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
                    break;
                else
                    continue;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            consumerRecords.forEach(record -> {
                //System.out.println("Record Key " + record.key());
                System.out.println(this.info + " ——— Record value " + record.value().getId() + " ——— " + record.value().getName());
                // System.out.println("Record partition " + record.partition());
                //System.out.println("Record offset " + record.offset());
            });
            consumer.commitAsync();
        }
        consumer.close();
    }

    void shutdown() {
        consumer.wakeup();
    }
}
