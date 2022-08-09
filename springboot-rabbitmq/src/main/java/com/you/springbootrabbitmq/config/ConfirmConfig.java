package com.you.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/9 11:26
 * 配置类 发布确认（高级）
 */
@Configuration
public class ConfirmConfig {
    public static final String CONFIRM_EXCHANGE = "confirm_exchange";
    public static final String CONFIRM_QUEUE = "confirm_queue";
    public static final String CONFIRM_ROUTING = "confirm_key";
    @Bean("confirmQueue")
    public Queue confirmQueue(){
        return new Queue(CONFIRM_QUEUE);
    }

    @Bean("confirmExchange")
    public DirectExchange confirmExchange(){
        return new DirectExchange(CONFIRM_EXCHANGE);
    }

    @Bean
    public Binding confirmQueueBindingExchange(@Qualifier("confirmQueue") Queue confirmQueue,
                                               @Qualifier("confirmExchange") DirectExchange confirmExhange){
        return BindingBuilder.bind(confirmQueue).to(confirmExhange).with(CONFIRM_ROUTING);
    }
}
