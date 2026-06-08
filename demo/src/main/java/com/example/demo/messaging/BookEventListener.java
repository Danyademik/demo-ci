package com.example.demo.messaging;

import com.example.demo.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BookEventListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleBookCreated(String message) {
        System.out.println("[Listener] Отримано: " + message);
        System.out.println("[Listener] Надсилаємо email-сповіщення про нову книгу...");
    }
}