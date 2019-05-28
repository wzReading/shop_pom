package com.qf.demo2;

import com.qf.util.RabbitConnectUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/24 16:34
 */
public class Consumer2 {
    public static void main(String[] args) throws Exception{

        // 创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 连接rabbitmq
        Connection connection = RabbitConnectUtil.getConnection();

        // 获取通道
        Channel channel = connection.createChannel();

        // 创建消息队列
        channel.queueDeclare("myqueue",false,false,false,null);

        // 监听队列
        channel.basicConsume("myqueue",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // 如果队列中有消息，就会回调这个方法
                            String str = new String(body,"utf-8");
                            System.out.println("接收到队列中的消息："+str);
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
