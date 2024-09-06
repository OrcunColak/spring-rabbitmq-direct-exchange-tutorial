package com.colak.springtutorial.service.producer;

import com.colak.springtutorial.config.OrderProcessingConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;

    public void processOrderPayment(String orderId) {
        // Simulate processing payment and send order to the payment queue
        // First parameter is exchange name, second parameter is routing key
        rabbitTemplate.convertAndSend(OrderProcessingConfig.ORDER_DIRECT_EXCHANGE, OrderProcessingConfig.ROUTING_KEY_PAYMENT, orderId);
    }

    public void processOrderInventory(String orderId) {
        // Simulate managing inventory and send order to the inventory queue
        // First parameter is exchange name, second parameter is routing key
        rabbitTemplate.convertAndSend(OrderProcessingConfig.ORDER_DIRECT_EXCHANGE, OrderProcessingConfig.ROUTING_KEY_INVENTORY, orderId);
    }

    public void processOrderShipping(String orderId) {
        // Simulate shipping and send order to the shipping queue
        // First parameter is exchange name, second parameter is routing key
        rabbitTemplate.convertAndSend(OrderProcessingConfig.ORDER_DIRECT_EXCHANGE, OrderProcessingConfig.ROUTING_KEY_SHIPPING, orderId);
    }
}
