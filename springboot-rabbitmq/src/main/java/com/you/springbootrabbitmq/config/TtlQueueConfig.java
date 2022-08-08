package com.you.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/8 9:39
 * TTL队列  配置文件类代码
 */
@Configuration
public class TtlQueueConfig {
    //普通交换机名称
    public static final String  X_EXCHANGE = "X";
    //死信交换机名称
    public static final String  DEAD_EXCHANGE = "Y";
    //普通队列
    public static final String  QUEUE_A = "QA";
    public static final String  QUEUE_B = "QB";
    public static final String  QUEUE_C = "QC";
    //routingKey
    public static final String  ROUTING_A = "XA";
    public static final String  ROUTING_B = "XB";
    public static final String  ROUTING_D = "YB";
    public static final String  ROUTING_C = "XC";
    //死信队列
    public static final String DEAD_QUEUE = "QD";

    @Bean("xExchange")
    public DirectExchange xExchange(){
        return new DirectExchange(X_EXCHANGE);
    }

    @Bean("yExchange")
    public DirectExchange deadExchange(){
        return new DirectExchange(DEAD_EXCHANGE);
    }

    @Bean("queueA")
    public Queue queueA(){
        Map<String,Object>  arguments = new HashMap<>(3);
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        //设置死信Routingkey
        arguments.put("x-dead-letter-routing-key","YD");
        //设置过期时间
        arguments.put("x-message-ttl",10000);
        return  QueueBuilder.durable(QUEUE_A).withArguments(arguments).build();
    }

    @Bean("queueB")
    public Queue queueB(){
        Map<String,Object>  arguments = new HashMap<>(3);
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        //设置死信Routingkey
        arguments.put("x-dead-letter-routing-key","YD");
        //设置过期时间
        arguments.put("x-message-ttl",40000);
        return  QueueBuilder.durable(QUEUE_B).withArguments(arguments).build();
    }

    @Bean("queueC")
    public Queue queueC(){
        Map<String,Object>  arguments = new HashMap<>(3);
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        //设置死信Routingkey
        arguments.put("x-dead-letter-routing-key","YD");
        return QueueBuilder.durable(QUEUE_C).withArguments(arguments).build();
    }
    //死信队列
    @Bean("queueD")
    public Queue queueD(){
        return QueueBuilder.durable(DEAD_QUEUE).build();
    }

    //绑定
    @Bean
    public Binding queueABindingX(@Qualifier("queueA") Queue queueA, @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");
    }

    @Bean
    public Binding queueBBindingX(@Qualifier("queueB") Queue queueB, @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueB).to(xExchange).with("XB");
    }

    @Bean
    public Binding queueDBindingY(@Qualifier("queueD") Queue queueD, @Qualifier("yExchange") DirectExchange yExchange){
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");
    }

    @Bean
    public Binding queueCBindingX(@Qualifier("queueC") Queue queueC,@Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueC).to(xExchange).with("XC");
    }
}
