package com.you.rabbitmq4;

import com.rabbitmq.client.Channel;
import com.you.util.RabbitMqUtil;

import java.util.Scanner;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/4 15:30
 * 消息发送者
 */
public class EmitLog {
    //交换机名称
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws  Exception{
        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        Scanner  scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish(EXCHANGE_NAME,"123",null,message.getBytes("UTF-8"));
            System.out.println("生产者发出消息--》"+message);
        }
    }
}
