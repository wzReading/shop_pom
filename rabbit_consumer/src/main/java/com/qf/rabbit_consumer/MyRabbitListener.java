package com.qf.rabbit_consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/24 22:03
 */
@Component
public class MyRabbitListener {

    @RabbitListener(queues = "sb_queue")
    public void myHandler(String msg){
        System.out.println(msg);
    }
}
