package io.b0b.ai;


import io.b0b.ai.pojo.MyObject;
import org.apache.kafka.clients.consumer.Consumer;
import io.b0b.ai.consumer.ConsumerCreator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class ConsumersApp {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        final List<ConsumerThread> consumers = new ArrayList<>();
        Consumer<Long, MyObject> consumer;
        for (int i = 0; i < 2; i++) {
            consumer = ConsumerCreator.createConsumer();
            ConsumerThread consumerThread = new ConsumerThread(consumer, "Thread—" + i);
            consumers.add(consumerThread);
            executor.submit(consumerThread);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (ConsumerThread consumerThread : consumers) {
                consumerThread.shutdown();
            }
            executor.shutdown();
            try {
                executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }
}
