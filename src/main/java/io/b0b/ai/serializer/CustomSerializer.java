package io.b0b.ai.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.b0b.ai.pojo.MyObject;

public class CustomSerializer implements Serializer<MyObject> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {

	}

	@Override
	public byte[] serialize(String topic, MyObject data) {
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsString(data).getBytes();
		} catch (Exception exception) {
			System.out.println("Error in serializing object" + data);
		}
		return retVal;
	}

	@Override
	public void close() {

	}

}