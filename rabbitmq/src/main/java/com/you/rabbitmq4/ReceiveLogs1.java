package com.you.rabbitmq4;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.you.util.RabbitMqUtil;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/3 16:45
 * 消息接收
 */
public class ReceiveLogs1 {
    //交换机名称
    public static final String EXCHANGE_NAME = "logs";
    public static void main(String[] args) throws  Exception{
        Channel channel = RabbitMqUtil.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        /**
         * 声明一个临时队列
         * 生成一个临时队列、队列的名称是随机的
         * 当消费者断开与队列的连接的时候，队列就自动删除了
         */
        String queueName = channel.queueDeclare().getQueue();
        /**
         * 绑定交换机和队列
         */
        channel.queueBind(queueName,EXCHANGE_NAME,"1232");
        System.out.println("ReceiveLogs1等待接收消息。。。。。。");
        //接收消息
        DeliverCallback  deliverCallback = (var1,var2)->{
            System.out.println("ReceiveLogs1接收到的消息："+ new String(var2.getBody(),"UTF-8"));
        };
        channel.basicConsume(queueName,true,deliverCallback,var1->{});
    }
}
