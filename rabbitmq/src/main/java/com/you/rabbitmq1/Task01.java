package com.you.rabbitmq1;

import com.rabbitmq.client.Channel;
import com.you.util.RabbitMqUtil;

import java.util.Scanner;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/7/25 11:54
 * 轮训测试
 */
public class Task01 {
    public static final String QUEUE_NAME =  "hello";

    public static void main(String[] args) throws Exception{

            Channel channel = RabbitMqUtil.getChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String message = scanner.next();
                channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
                System.out.println("消息发送完毕！！");
            }

    }
}
