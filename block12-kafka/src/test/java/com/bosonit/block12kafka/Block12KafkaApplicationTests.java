package com.bosonit.block12kafka;

import com.bosonit.block12kafka.kafka.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Block12KafkaApplicationTests {
	@Autowired
	Producer producer;
	@Test
	void contextLoads() throws JsonProcessingException {
		producer.sendCustomer();
	}

}
