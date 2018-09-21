package io.b0b.ai.deserializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.b0b.ai.pojo.MyObject;

public class CustomDeserializer implements Deserializer<MyObject> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public MyObject deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        MyObject object = null;
        try {
            object = mapper.readValue(data, MyObject.class);
        } catch (Exception exception) {
            System.out.println("Error in deserializing bytes " + exception);
        }
        return object;
    }

    @Override
    public void close() {
    }
}