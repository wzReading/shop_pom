package com.qf.demo2;

import com.qf.util.RabbitConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/24 16:17
 */
public class Provider2 {
    /**
     * 测试rabbitMQ消息队列传输是同步还是异步
     * 同步：一个任务提交后，需要等任务中的子任务任务完成才能继续执行下去
     * 异步：一个任务提交后，将子任务提交出去，继续往下执行
     * 当前案例结果：同步
     * @param args
     */
    public static void main(String[] args) throws Exception{
        // 1、远程连接RabbitMQ
        Connection connection = RabbitConnectUtil.getConnection();

        // 2.获取通道
        Channel channel = connection.createChannel();

        // 3、创建消息队列
        channel.queueDeclare("myqueue",false,false,false,null);

        // 4、给队列中发送消息
        for (int i = 0; i < 10; i++){
            String msg = "Hello RabbitMQ " + i;
            channel.basicPublish("","myqueue",null,msg.getBytes("utf-8"));
        }
        System.out.println("执行完毕");
        connection.close();
    }
}
