package com.qf.demo5;

import com.qf.util.RabbitConnectUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/24 21:19
 */
public class Consumer5_2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitConnectUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicConsume("myqueue2",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routingKey = envelope.getRoutingKey();// 获取路由键的名称
                System.out.println(routingKey+"-myqueue2接收的信息为： "+new String(body,"utf-8"));
            }
        });
    }
}
