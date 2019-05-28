package com.qf.rabbitmq_provider;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/24 21:53
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue getQueue(){
        return new Queue("sb_queue");
    }
}
