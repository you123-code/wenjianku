package com.you.springbootrabbitmq.consumer;

import com.you.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/9 13:49
 */
@Slf4j
@Component
public class ConfirmConsumer {
    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE)
    public void receiveConfirm(Message message){
        log.info("接收到confirmQueue的消息为：{}",new String(message.getBody()));
    }
}
