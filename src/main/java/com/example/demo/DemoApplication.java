package com.example.demo;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		MeterRegistry registry = new SimpleMeterRegistry();

		MetricsExample metrics = new MetricsExample(registry);

		// Cập nhật số lượng thread đang hoạt động
		metrics.updateActiveThreads(10);
		SpringApplication.run(DemoApplication.class, args);
	}

}

