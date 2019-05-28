package com.qf.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/24 11:16
 */
public class RabbitConnectUtil {
    public static ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.134.128");
        connectionFactory.setVirtualHost("reading");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("reading");
        connectionFactory.setPassword("123");
    }

    public static Connection getConnection(){
        try {
            return  connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

}
