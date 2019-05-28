package com.qf.demo3;

import com.qf.util.RabbitConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/24 16:47
 */
public class Provider3 {
    /**
     * 当前案例测试rabbitmQ消息队列是广播的模式还是轮询的方式发送信息
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // 1、连接RabbitMQ
        Connection connection = RabbitConnectUtil.getConnection();

        // 获取通道对象
        Channel channel = connection.createChannel();

        // 创建队列
        channel.queueDeclare("myqueue",false,false,false,null);

        String msg = "Hello RabbitMQ";
        channel.basicPublish("","myqueue",null,msg.getBytes());

        connection.close();

        System.out.println("当前服务提供者已经执行完毕");
    }
}
