package com.you.rabbitmq2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
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
        channel.confirmSelect();
        /**
         * 声明队列
         * 1.第二个参数声明队列是否持久化，ture：持久化
         */
        channel.queueDeclare(TASK_QUEUE_NAME,true,false,false,null);
        //控制台输入
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            /**设置生产者发送消息为持久化消息（存在磁盘上），一般不声明的话保存在内存中
             * 第二个参数配置消息是否持久化，null（无配置）， MessageProperties.PERSISTENT_TEXT_PLAIN(持久化)
             */
            channel.basicPublish("",TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes("UTF-8"));
            System.out.println("生产者发出消息---》"+message);
        }
    }
}
