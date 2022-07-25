package com.you.rabbitmq1;

import com.rabbitmq.client.*;
import com.you.util.RabbitMqUtil;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/7/25 11:30
 * 多线程开启消费者
 */
public class Worker01 {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
            System.out.println("worker03等待接收消息。。。。。。");
            Channel channel = RabbitMqUtil.getChannel();
            //声明接收消息
            DeliverCallback deliverCallback = (var1, var2) ->{
                System.out.println("worker01接收到的消息---》"+new String(var2.getBody()));
            };
            //取消消息的回调
            CancelCallback cancelCallback = var1 ->{
                System.out.println("worker01接收到的消息---》"+"消费被中断触发回调！！");
            };

            channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}
