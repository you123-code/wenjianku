package com.you.rabbitmq5;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.you.util.RabbitMqUtil;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/4 17:05
 */
public class ReceiveLogsDirect00 {
    public static final String EXCHANGE_NAME = "direct-logs";

    public static void main(String[] args)  throws  Exception{
        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueDeclare("console",false,false,false,null);
        channel.queueBind("console",EXCHANGE_NAME,"info");
        channel.queueBind("console",EXCHANGE_NAME,"warning");
        //接收消息
        DeliverCallback deliverCallback = (vlu1,vlu2)->{
            System.out.println("ReceiveLogsDirect00接收到的信息为--》"+new String(vlu2.getBody(),"UTF-8"));
        };
        //消费者取消消息时回调接口
        channel.basicConsume("console",true,deliverCallback,vlu1 ->{});

    }
}
