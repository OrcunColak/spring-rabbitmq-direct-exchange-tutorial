package com.colak.springtutorial.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Creates an exchange with 3 queues.
// Each key has a different routing key
@Configuration
public class OrderProcessingConfig {

    public static final String ORDER_DIRECT_EXCHANGE = "order-direct-exchange";

    // Queues
    public static final String PAYMENT_QUEUE = "payment-queue";
    public static final String INVENTORY_QUEUE = "inventory-queue";
    public static final String SHIPPING_QUEUE = "shipping-queue";

    // Routing keys for different order processing stages
    public static final String ROUTING_KEY_PAYMENT = "payment";
    public static final String ROUTING_KEY_INVENTORY = "inventory";
    public static final String ROUTING_KEY_SHIPPING = "shipping";

    @Bean
    public DirectExchange orderDirectExchange() {
        // Instead of "return new DirectExchange(ORDER_DIRECT_EXCHANGE);"
        // use ExchangeBuilder

        return ExchangeBuilder.directExchange(ORDER_DIRECT_EXCHANGE)
                // durable means messages will survive broker restarts
                .durable(true)
                // the broker deletes the queue when the last consumer unsubscribes (i.e., it's not connected to any queues).
                // If set to false, the exchange will persist even if it's not bound to any queues.
                .autoDelete()
                .build();
    }

    @Bean
    public Queue paymentQueue() {
        return new Queue(PAYMENT_QUEUE);
    }

    @Bean
    public Queue inventoryQueue() {
        return new Queue(INVENTORY_QUEUE);
    }

    @Bean
    public Queue shippingQueue() {
        return new Queue(SHIPPING_QUEUE);
    }

    @Bean
    public Binding paymentBinding() {
        return BindingBuilder.bind(paymentQueue()).to(orderDirectExchange()).with(ROUTING_KEY_PAYMENT);
    }

    @Bean
    public Binding inventoryBinding() {
        return BindingBuilder.bind(inventoryQueue()).to(orderDirectExchange()).with(ROUTING_KEY_INVENTORY);
    }

    @Bean
    public Binding shippingBinding() {
        return BindingBuilder.bind(shippingQueue()).to(orderDirectExchange()).with(ROUTING_KEY_SHIPPING);
    }
}
