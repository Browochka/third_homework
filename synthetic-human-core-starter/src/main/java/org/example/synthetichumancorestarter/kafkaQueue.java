package org.example.synthetichumancorestarter;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
@RequiredArgsConstructor

public class kafkaQueue {
    private final BlockingQueue<transferObject> queue =  new LinkedBlockingQueue<transferObject>(10);
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final MeterRegistry meterRegistry;
    private final AtomicInteger processedCount = new AtomicInteger(0);

    @PostConstruct
    public void startProcessing() {
        executor.submit(() -> {
            while (true) {
                try {
                    transferObject command = queue.take();
                    log.info("Выполнена обычная команда {}", command);
                    processedCount.incrementAndGet();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        meterRegistry.gauge("queue.size", queue, BlockingQueue::size);
        meterRegistry.gauge("processed.count", processedCount);
    }

    public void unqueue(transferObject command) {
        if (!queue.offer(command)) {
            throw new RuntimeException("Очередь переполнена");
        }
    }
    public int getQueueSize() {
        return queue.size();
    }
}
