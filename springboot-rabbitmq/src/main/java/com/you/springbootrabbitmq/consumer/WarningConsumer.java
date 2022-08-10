package com.you.springbootrabbitmq.consumer;

import com.you.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/10 10:16
 * 报警消费者
 */
@Slf4j
@Component
public class WarningConsumer {
    @RabbitListener(queues = ConfirmConfig.WARNING_QUEUE)
    public void receiveWarningMsg(Message message){
        String msg = new String(message.getBody());
        log.error("报警发现不可路由的消息为：{}",msg );
    }
}
