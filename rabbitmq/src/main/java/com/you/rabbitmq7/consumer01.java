package com.you.rabbitmq7;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.you.util.RabbitMqUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/5 15:54
 * 死信队列实战
 * 消费者
 */
public class consumer01 {
    //普通交换机
    public static final String NORMAL_EXCHANGE = "normal_exchange";
    //死信交换机
    public static final String DEAD_EXCHANGE = "dead_exchange";
    //普通队列
    public static final String NORMAL_QUEUE = "normal_queue";
    //死信队列
    public static final String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtil.getChannel();
        //声明普通和死信交换机
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
        //声明普通和死信队列
        Map<String,Object> arguments = new HashMap<>();
        //设置过期时间
        //arguments.put("x-message-ttl",100000);
        //正常队列设置死信交换机
        arguments.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        //设置死信RoutingKey
        arguments.put("x-dead-letter-routing-key","lisi");
        //设置队列最大长度
        //arguments.put("x-max-length",6);
        channel.queueDeclare(NORMAL_QUEUE,false,false,false,arguments);
        //--------------------------------------------------------------------------------
        channel.queueDeclare(DEAD_QUEUE,false,false,false,null);
        //绑定普通的交换机和队列
        channel.queueBind(NORMAL_QUEUE,NORMAL_EXCHANGE,"zhangsan");
        //绑定死信的交换机和队列
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"lisi");
        System.out.println("等待接收消息。。。。");
        DeliverCallback deliverCallback = (val1,val2)->{
            String str = new String(val2.getBody(),"UTF-8");
            if("info1".equals(str)){
                System.out.println("consumer01拒绝的消息为---》"+str);
                channel.basicReject(val2.getEnvelope().getDeliveryTag(),false);
            }else{
                System.out.println("consumer01获取的消息为---》"+str);
                channel.basicAck(val2.getEnvelope().getDeliveryTag(),false);
            }
        };
        //拒绝开发手动应答false
        channel.basicConsume(NORMAL_QUEUE,false,deliverCallback,val1->{});
    }
 }
