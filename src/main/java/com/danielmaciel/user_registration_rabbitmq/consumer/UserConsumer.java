package com.danielmaciel.user_registration_rabbitmq.consumer;

import com.danielmaciel.user_registration_rabbitmq.model.User;
import com.danielmaciel.user_registration_rabbitmq.rabbitmq.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {
    @RabbitListener(queues = RabbitMQConfig.USER_QUEUE)
    public void receive(User user) throws InterruptedException {
        System.out.println("User Received: "+ user.getEmail());
        System.out.println("Sending Welcome Email...: "+ user.getEmail());

        Thread.sleep(2000);
        System.out.println("Email Sent!");

    }
}
