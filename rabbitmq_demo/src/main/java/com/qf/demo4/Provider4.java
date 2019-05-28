package com.qf.demo4;

import com.qf.util.RabbitConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/24 17:00
 */
public class Provider4 {
    /**
     * 案例4：RabbitMQ中的交换机案例
     * 一个交换机可以绑定多个队列，交换机也能绑定交换机
     * consumer应该先启动才能接受到队列信息
     *
     * 总结：当消息提供者给交换机发送信息的时候，交换机下的所有队列和交换机都会接收到信息
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // 1、连接Rabbitmq
        Connection connection = RabbitConnectUtil.getConnection();

        // 2、get channel
        Channel channel = connection.createChannel();

        // 3、创建一个交换机
        // 交换机的名称和交换机的类型
        channel.exchangeDeclare("myexchange","fanout");

        // 4、给队列中发送消息
        String msg = "Hello RabbitMQ Fanout";
        channel.basicPublish("myexchange","",null,msg.getBytes("utf-8"));

        connection.close();
    }
}
