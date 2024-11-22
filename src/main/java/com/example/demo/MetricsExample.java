package com.example.demo;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.concurrent.atomic.AtomicInteger;

public class MetricsExample {
    private final MeterRegistry registry;
    private final AtomicInteger activeThreads;

    public MetricsExample(MeterRegistry registry) {
        this.registry = registry;
        this.activeThreads = new AtomicInteger(0);

        // Đăng ký gauge
        Gauge.builder("active.threads", activeThreads, AtomicInteger::get)
                .description("Number of active threads")
                .tags("status", "active")
                .register(registry);
    }

    public void updateActiveThreads(int count) {
        activeThreads.set(count);
    }
}
