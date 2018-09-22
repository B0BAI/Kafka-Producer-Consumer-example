package io.b0b.ai;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.b0b.ai.pojo.MyObject;
import org.apache.kafka.clients.producer.Producer;
import io.b0b.ai.producer.ProducerCreator;


public class ProducerAPP {

    public static void main(String[] args) {
        Producer<Long, MyObject> producer;
        int start = 0, end = 2, numberOfProducers = 5;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfProducers);

        for (int index = 0; index < numberOfProducers; ++index) {
            producer = ProducerCreator.createProducer();
            ProducerThread multipleProducers = new ProducerThread(producer, start, end, "Producer Thread — " + index);
            executor.submit(multipleProducers);
            start = end;
            end += 3;
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executor.shutdown();
            try {
                executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }
}
