package com.you.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/7/25 10:08
 * 消费者
 */
public class Consumer {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.211.132");
        factory.setUsername("admin");
        factory.setPassword("123");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            //声明接收消息
            DeliverCallback deliverCallback = ( var1,  var2) ->{
                System.out.println(new String(var2.getBody()));
            };
            //取消消息的回调
            CancelCallback cancelCallback = var1 ->{
                System.out.println("消费被中断触发回调！！");
            };
            /**
             *1.消费那个队列
             *2.消费成功之后之后是否自动应答，true（自动应答），false（手动应答）
             *3.消费者未成功消费的回调
             *4.消费者取消消息的回调
             */
            channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
