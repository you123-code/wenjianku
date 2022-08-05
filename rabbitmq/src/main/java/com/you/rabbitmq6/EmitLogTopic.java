package com.you.rabbitmq6;

import com.rabbitmq.client.Channel;
import com.you.util.RabbitMqUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/5 14:27
 * 生产者
 */
public class EmitLogTopic {
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws  Exception{
        Channel channel = RabbitMqUtil.getChannel();
        Map<String,String> bindingMap = new HashMap<>();
        bindingMap.put("quick.orange.rabbit","被队列Q1Q2接收");
        bindingMap.put("lazy.orange.elephant","被队列Q1Q2接收");
        bindingMap.put("quick.orange.fox","被队列Q1接收");
        bindingMap.put("lazy.pink.rabbit","只被队列Q2接收");
        for (Map.Entry<String, String> stringStringEntry : bindingMap.entrySet()) {
            String routingKey = stringStringEntry.getKey();
            String message = stringStringEntry.getValue();
            channel.basicPublish(EXCHANGE_NAME,routingKey,null,message.getBytes("UTF-8"));
            System.out.println("生产者发送消息----》"+message);
        }

    }
}
