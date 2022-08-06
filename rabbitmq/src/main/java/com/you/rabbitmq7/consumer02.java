package com.you.rabbitmq7;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.you.util.RabbitMqUtil;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/5 15:54
 * 死信队列实战
 * 消费者
 */
public class consumer02 {
    //死信交换机
    public static final String DEAD_EXCHANGE = "dead_exchange";
    //死信队列
    public static final String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtil.getChannel();

        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"lisi");
        System.out.println("等待接收消息。。。。");
        DeliverCallback deliverCallback = (val1,val2)->{
            System.out.println("consumer02获取的消息为---》"+new String(val2.getBody(),"UTF-8"));
        };
        channel.basicConsume(DEAD_QUEUE,true,deliverCallback,val1->{});
    }
 }
