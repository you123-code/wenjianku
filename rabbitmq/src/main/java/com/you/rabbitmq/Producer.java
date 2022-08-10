package com.you.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/7/23 17:36
 * rabbitmq,消费者和生产者创建
 */
public class Producer {
    public static final String QUEUE_NAME =  "hello";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.211.132");
        factory.setUsername("admin");
        factory.setPassword("123");
        //创建连接
        try {
            Connection connection = factory.newConnection();
            //获取信道
            Channel channel = connection.createChannel();
            /**
             * 生成一个队列，
             * 1.队列名称
             * 2.队列里的消息是否持久化（磁盘），默认存储在内存,true持久化
             * 3.该队列是否只供一个消费者进行消费，是否进行消息共享，true开启消息共享
             * 4.是否自动删除，最后一个消费者断开连接之后，该队列是否自动删除，true自动删除，false不自动删除
             * 5.其他参数
             */
            Map<String,Object> arguments = new HashMap<>();
            //官方允许配置0-255之间，设置过大CPU和内存造成浪费
            arguments.put("x-max-priority",10);
            channel.queueDeclare(QUEUE_NAME,true,false,false,arguments);
            for (int i = 1; i < 11; i++) {
                String msg = "info"+i;
                if(i == 5){
                    AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().priority(5).build();
                    channel.basicPublish("",QUEUE_NAME,properties,msg.getBytes());
                }else{
                    channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
                }
            }
            //发消息
            String message = "hello world!";
            /**
             * 发送一个消费
             * 1.发送到那个交换机
             * 2.路由的key值是哪个，本次为队列的名称
             * 3.其它参数信息
             * 4.发送消息的消息体
             */
            //channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("消息发送完毕！！");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
