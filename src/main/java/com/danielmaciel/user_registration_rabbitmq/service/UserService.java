package com.danielmaciel.user_registration_rabbitmq.service;

import com.danielmaciel.user_registration_rabbitmq.model.User;
import com.danielmaciel.user_registration_rabbitmq.rabbitmq.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RabbitTemplate rabbitTemplate;
    private final List<User> users = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public User create(User user){
        user.setId(counter.incrementAndGet());
        users.add(user);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.USER_EXCHANGE,
                RabbitMQConfig.USER_ROUTING_KEY,
                user
        );

        return user;
    }

    public List<User> findAll(){
        return users;
    }
}
