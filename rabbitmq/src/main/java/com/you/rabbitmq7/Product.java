package com.you.rabbitmq7;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.you.util.RabbitMqUtil;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/5 18:56
 * 死信队列生产者
 */
public class Product {
    //普通交换机
    public static final String NORMAL_EXCHANGE = "normal_exchange";

    public static void main(String[] args) throws  Exception{
        Channel channel = RabbitMqUtil.getChannel();
        //死信消息，设置TTL时间（存活时间）
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();
        for (int i = 0; i < 10; i++) {
            String message = "info"+i;
            channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",properties,message.getBytes());
            System.out.println("发出消息----》"+message);
        }
    }
}
