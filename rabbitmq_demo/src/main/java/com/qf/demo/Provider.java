package com.qf.demo;

import com.qf.util.RabbitConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/24 11:27
 */
public class Provider  {
    public static void main(String[] args) throws IOException {

        // 连接rabbit
        Connection connection = RabbitConnectUtil.getConnection();

        // 获取通道
        Channel channel = connection.createChannel();

        // 创建消息队列
        channel.queueDeclare("myqueue",false,false,false,null);

        // 给队列发送消息
        String msg = "hello RabbitMQ";
        channel.basicPublish("","myqueue",null,msg.getBytes("utf-8"));
        connection.close();
    }
}
