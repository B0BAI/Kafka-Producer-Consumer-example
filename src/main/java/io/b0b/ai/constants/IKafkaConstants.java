package io.b0b.ai.constants;

public interface IKafkaConstants {
    String KAFKA_BROKERS = "localhost:9092";

    Integer MESSAGE_COUNT = 1000;

    String CLIENT_ID = "client1";

    String TOPIC_NAME = "demo2";

    String GROUP_ID_CONFIG = "consumerGroup10";

    Integer MAX_NO_MESSAGE_FOUND_COUNT = 100;

    String OFFSET_RESET_LATEST = "latest";

    String OFFSET_RESET_EARLIER = "earliest";

    Integer MAX_POLL_RECORDS = 1;
    //properties.put("auto.offset.reset", "smallest")
}
