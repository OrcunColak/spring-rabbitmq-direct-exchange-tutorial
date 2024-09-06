package com.colak.springtutorial.controller;

import com.colak.springtutorial.service.producer.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/rabbitmq")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer orderProducer;

    // http://localhost:8080/api/rabbitmq/sendMessage/Hello
    @GetMapping("/sendMessage/{message}")
    public ResponseEntity<String> testRabbitmq(@PathVariable String message) {
        orderProducer.processOrderPayment(message);
        orderProducer.processOrderInventory(message);
        orderProducer.processOrderShipping(message);

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
