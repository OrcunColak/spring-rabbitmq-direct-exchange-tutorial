package com.colak.springtutorial.service.consumer;

import com.colak.springtutorial.config.OrderProcessingConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentConsumer {

    @RabbitListener(queues = OrderProcessingConfig.PAYMENT_QUEUE)
    public void processPayment(String orderId) {
        // Process payment logic for the order
        log.info("Payment processed for order: {}", orderId);
    }
}
