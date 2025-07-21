package org.example.synthetichumancorestarter.audit;

import org.slf4j.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class auditAspect {
    private final KafkaProducer<String, String> kafkaProducer;
    @Value("${audit.kafka.topic}")
    private String kafkaTopic;
    @Value("${audit.mode:console}")
    private String AuditMode;

    @Around("@annotation(org.example.synthetichumancorestarter.audit.WeylandWatchingYou)")
    public Object audit(ProceedingJoinPoint JoinPoint) throws Throwable {
        String methodName = JoinPoint.getSignature().getName();
        String params = JoinPoint.getArgs().toString();

        Object result = JoinPoint.proceed();
        String logMessage = String.format("Method: %s | Params: %s | Result: %s", methodName, params, result);

        if ("kafka".equals(AuditMode)) {
            kafkaProducer.send(new ProducerRecord<>(kafkaTopic,logMessage));
        } else {
            log.info("[AUDIT] {}",logMessage);

        }
        return result;
    }
}
