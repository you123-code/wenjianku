package com.you.springbootrabbitmq.consumer;

import com.you.springbootrabbitmq.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/9 10:33
 * 基于 插件延时队列消费者
 */
@Slf4j
@Component
public class DelayQueueConsumer {
    //监听消息
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE)
    public void  receiverDelayQueue(Message  message){
        String msg = new String(message.getBody());
        log.info("当前时间：{},收到延迟队列的消息：{}",new Date().toString(),msg);
    }
}
