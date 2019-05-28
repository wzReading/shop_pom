package com.qf.demo;

import com.qf.util.RabbitConnectUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/24 15:57
 */
public class Consumer {
    public static void main(String[] args) throws Exception{

        // 1、连接rabbitmq
        Connection connection = RabbitConnectUtil.getConnection();

        // 2、创建消息队列
        Channel channel = connection.createChannel();

        // 3|通过通道创建一个队列
        channel.queueDeclare("myqueue",false,false,false,null);

        // 4、监听队列
        channel.basicConsume("myqueue",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // body为消息队列中的信息
                String str = new String(body,"utf-8");
                System.out.println("接收队伍中的消息："+str);
            }
        });
    }
}
