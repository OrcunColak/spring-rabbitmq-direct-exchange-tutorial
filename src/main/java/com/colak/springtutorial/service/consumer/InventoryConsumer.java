package com.colak.springtutorial.service.consumer;

import com.colak.springtutorial.config.OrderProcessingConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InventoryConsumer {

    @RabbitListener(queues = OrderProcessingConfig.INVENTORY_QUEUE)
    public void manageInventory(String orderId) {
        // Manage inventory logic for the order
        log.info("Inventory managed for order: {}", orderId);
    }
}
