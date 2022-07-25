package com.you.rabbitmq2;

import com.rabbitmq.client.Channel;
import com.you.util.RabbitMqUtil;

import java.util.Scanner;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/7/25 14:06
 * 消息在手动应答时是不丢失的，放回消息队列中重新消费
 */
public class Task02 {
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtil.getChannel();
        //声明队列
        channel.queueDeclare(TASK_QUEUE_NAME,false,false,false,null);
        //控制台输入
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("",TASK_QUEUE_NAME,null,message.getBytes("UTF-8"));
            System.out.println("生产者发出消息---》"+message);
        }
    }
}
