package com.you.rabbitmq6;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.you.util.RabbitMqUtil;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/4 18:44
 * 声明主题交换机和相关队列
 *
 * 消费者01创建
 */
public class ReceiveLogsTopic01 {
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String queueName = "Q1";
        String routingKey = "*.orange.*";
        //是否持久化，是否共享，是否自动删除
        channel.queueDeclare(queueName,false,false,false,null);
        channel.queueBind(queueName,EXCHANGE_NAME,routingKey);
        System.out.println("等待接收消息。。。。。。");
        //接收消息
        DeliverCallback deliverCallback = (val1,val2)->{
            System.out.println("ReceiveLogsTopic01"+"queueName-->"+queueName+"routingKey-->"+routingKey+"接收到的消息为---》"+ new String(val2.getBody(),"UTF-8"));
        };
        channel.basicConsume(queueName,true,deliverCallback,val1->{});
    }
}
