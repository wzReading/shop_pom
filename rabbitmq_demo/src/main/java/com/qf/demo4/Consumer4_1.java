package com.qf.demo4;

import com.qf.util.RabbitConnectUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/24 17:07
 */
public class Consumer4_1 {
    public static void main(String[] args) throws IOException {
        // 获取RabbitMQ连接对象
        Connection connection = RabbitConnectUtil.getConnection();

        // 获取通道
        Channel channel = connection.createChannel();

        // 创建队列
        channel.queueDeclare("myqueue",false,false,false,null);
        // 将队列绑定交换机
        channel.queueBind("myqueue","myexchange",""); // 将队列绑定到交换机上

        // 监听队列
        channel.basicConsume("myqueue",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 如果队列中有消息，就会调用这个方法
                String str = new String(body,"utf-8");
                System.out.println("接收到队列中的消息："+str);
            }
        });
    }
}
