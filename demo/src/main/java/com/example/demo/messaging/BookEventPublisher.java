package com.example.demo.messaging;

import com.example.demo.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishBookCreated(String bookTitle) {
        String message = "Нову книгу створено: " + bookTitle;
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message);
        System.out.println("[Publisher] Повідомлення відправлено: " + message);
    }
}