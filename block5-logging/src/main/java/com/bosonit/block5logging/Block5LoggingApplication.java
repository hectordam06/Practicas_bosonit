package com.bosonit.block5logging;

import ch.qos.logback.classic.filter.ThresholdFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class Block5LoggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block5LoggingApplication.class, args);
	}

}
