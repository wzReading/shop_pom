package com.qf.demo5;

import com.qf.util.RabbitConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/24 20:40
 */
public class Provider5 {
    /**
     * 交换机和队列绑定时需要制定一个路由键，发送消息时也需要通过路由键决定这个消息发往哪些队列
     * 如果交换机类型是fanout，则所有路由键失效，编程全局广播
     * @param args
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取RabbitMQ远程连接对象
        Connection connection = RabbitConnectUtil.getConnection();

        // 获取通道
        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare("my_direct_exchange","direct");

        // 声明队列
        channel.queueDeclare("myqueue1",false,false,false,null);
        channel.queueDeclare("myqueue2",false,false,false,null);

        // 绑定队列和交换机
        channel.queueBind("myqueue1","my_direct_exchange","insert");
        channel.queueBind("myqueue1","my_direct_exchange","update");
        channel.queueBind("myqueue1","my_direct_exchange","delete");

        channel.queueBind("myqueue2","my_direct_exchange","insert");
        channel.queueBind("myqueue2","my_direct_exchange","select");

        // 发布消息
        channel.basicPublish("my_direct_exchange","insert",null,"Hello World!".getBytes("utf-8"));

        System.out.println("消息提供者信息发送结束");
        channel.close();
    }
}
