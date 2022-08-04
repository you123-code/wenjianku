package com.you.rabbitmq5;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.you.util.RabbitMqUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/4 18:07
 */
public class DirectLogs {
    //交换机名称
    public static final String EXCHANGE_NAME = "direct-logs";

    public static void main(String[] args) throws  Exception{
        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String mes = scanner.next();
            List<String> list = Arrays.asList(mes.split(","));
            channel.basicPublish(EXCHANGE_NAME,list.get(0),null,list.get(1).getBytes("UTF-8"));
            System.out.println("生产者发出消息--》"+ list.get(1));
        }
    }
}
