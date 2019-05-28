package com.qf.demo6;

import com.qf.util.RabbitConnectUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/24 21:43
 */
public class Consumer_2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitConnectUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume("myqueue2",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routingKey = envelope.getRoutingKey();
                System.out.println(routingKey+"-myqueue2接收到信息： "+new String(body,"utf-8"));
            }
        });
    }
}
