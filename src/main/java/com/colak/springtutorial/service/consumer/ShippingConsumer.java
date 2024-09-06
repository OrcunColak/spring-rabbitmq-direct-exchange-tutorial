package com.colak.springtutorial.service.consumer;

import com.colak.springtutorial.config.OrderProcessingConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ShippingConsumer {

    @RabbitListener(queues = OrderProcessingConfig.SHIPPING_QUEUE)
    public void handleShipping(String orderId) {
        // Handle shipping logic for the order
        log.info("Order shipped: {}", orderId);
    }
}
