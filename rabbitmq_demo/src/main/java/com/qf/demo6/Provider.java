package com.qf.demo6;

import com.qf.util.RabbitConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/24 21:30
 */
public class Provider {
    /**
     * topic交换机支持通配符的路由键
     * *表示一个单词 #表示0或者多个单词
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitConnectUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("mytopic_exchange","topic");

        // 声明队列
        channel.queueDeclare("myqueue1",false,false,false,null);
        channel.queueDeclare("myqueue2",false,false,false,null);

        // 绑定队列和交换机
        channel.queueBind("myqueue1","mytopic_exchange","a.*");
        channel.queueBind("myqueue2","mytopic_exchange","a.#");

        // 发布信息
        channel.basicPublish("mytopic_exchange","a.b.c",null,"Hello World".getBytes("utf-8"));

        connection.close();
    }
}
