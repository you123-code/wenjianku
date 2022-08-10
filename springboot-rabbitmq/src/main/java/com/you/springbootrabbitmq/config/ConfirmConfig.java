package com.you.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
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
    //确认交换机
    public static final String CONFIRM_EXCHANGE = "confirm_exchange";
    public static final String CONFIRM_QUEUE = "confirm_queue";
    public static final String CONFIRM_ROUTING = "confirm_key";
    //备份交换机
    public static final String BACKUP_EXCHANGE = "backup_exchange";
    public static final String BACKUP_QUEUE = "backup_queue";
    public static final String WARNING_QUEUE = "warning_queue";
    @Bean("confirmQueue")
    public Queue confirmQueue(){
        return new Queue(CONFIRM_QUEUE);
    }

    @Bean("confirmExchange")
    public DirectExchange confirmExchange(){
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE).durable(true).withArgument("alternate-exchange",BACKUP_EXCHANGE).build();
    }

    @Bean
    public Binding confirmQueueBindingExchange(@Qualifier("confirmQueue") Queue confirmQueue,
                                               @Qualifier("confirmExchange") DirectExchange confirmExhange){
        return BindingBuilder.bind(confirmQueue).to(confirmExhange).with(CONFIRM_ROUTING);
    }

    @Bean("backupQueue")
    public Queue backupQueue(){
        return new Queue(BACKUP_QUEUE);
    }

    @Bean("warningQueue")
    public Queue warningQueue(){
        return new Queue(WARNING_QUEUE);
    }

    @Bean("backupExchange")
    public FanoutExchange backupExchange(){
        return new FanoutExchange(BACKUP_EXCHANGE);
    }

    @Bean
    public Binding bindingBackuToBackup(@Qualifier("backupQueue") Queue backupQueue,@Qualifier("backupExchange") FanoutExchange backupExchange ){
        return BindingBuilder.bind(backupQueue).to(backupExchange);
    }

    @Bean
    public Binding bindingWarningToBackup(@Qualifier("warningQueue") Queue warningQueue,@Qualifier("backupExchange") FanoutExchange backupExchange ){
        return BindingBuilder.bind(warningQueue).to(backupExchange);
    }

}
