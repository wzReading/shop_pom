package com.qf.demo3;

import com.qf.util.RabbitConnectUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/24 16:52
 */
public class Consumer3_1 {
    public static void main(String[] args) throws IOException {

        // 获取连接对象
        Connection connection = RabbitConnectUtil.getConnection();

        // 通过连接对象获取通道
        Channel channel = connection.createChannel();

        // 创建消息队列
        channel.queueDeclare("myqueue",false,false,false,null);

        // 监听队列
        channel.basicConsume("myqueue",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 如果队伍中有消息，就会调用这个方法
                String str = new String(body);
                System.out.println("接收到队伍中的信息："+str);
            }
        });
    }
}
