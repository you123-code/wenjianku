package com.you.springbootrabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/9 14:34
 */
@Slf4j
@Component
public class MyCallBack implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        //注入MyCallBack---->rabbitTemplate
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    /**
     * 交换机确认回调方法
     * 1.发消息 交换机接收到了  回调
     *    1.1 correlationData 保存回调消息的id和相关信息
     *    1.2 交换机收到消息 ack=true
     *    1.3 reason = null
     *2.发消息 交换机接收失败  回调
     *    2.1 correlationData 保存回调消息的id和相关信息
     *    2.2 交换机未接收到消息  ack=false
     *    2.3 reason = 失败的原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String reason) {
        String id = correlationData.getId() != null?correlationData.getId():"";
        if(ack){
            log.info("交换机已经收到的消息的id为：{}",id);
        }else{
            log.info("交换机未收的消息id为：{},未接收到的原因为：{}",id,reason);
        }
    }

    //回退：可以当消息传递过程中不可达到目的地时将消息返回给生产者（前提是不可达到目的地的时候）
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.info("消息{}，被交换机{}退回，退回原因：{}，路由key：{}",new String(returnedMessage.getMessage().getBody()),returnedMessage.getExchange()
        ,returnedMessage.getReplyText(),returnedMessage.getRoutingKey());

    }
}
