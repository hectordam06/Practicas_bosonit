package com.bosonit.block5logging;

import ch.qos.logback.classic.filter.ThresholdFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig {

	@Bean
	public ThresholdFilter thresholdFilter() {
		ThresholdFilter thresholdFilter = new ThresholdFilter();
		thresholdFilter.setLevel("ERROR"); // Set log level to "ERROR"
		return thresholdFilter;
	}
}
