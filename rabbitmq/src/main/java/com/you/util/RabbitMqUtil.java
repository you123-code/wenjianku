package com.you.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/7/25 11:10
 */
public class RabbitMqUtil {
    public static Channel getChannel(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.211.132");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            return  channel;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
