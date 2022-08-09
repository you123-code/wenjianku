package com.you.springbootrabbitmq.controller;

import com.you.springbootrabbitmq.config.DelayedQueueConfig;
import com.you.springbootrabbitmq.config.TtlQueueConfig;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/8 11:12
 * 消息发送
 */
@Slf4j
@RestController
@RequestMapping("/ttl")
public class SendMsgController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //开始发消息
    @GetMapping("/sendMsg")
    @ApiOperation("发送消息")
    @ApiImplicitParams(
        @ApiImplicitParam(name = "message",value = "消息内容")
    )
    public void sendMsg1(String message){
        log.info("当前时间发送：{}，发送一条消息给两个TTL队列:{}",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),message);
        rabbitTemplate.convertAndSend(TtlQueueConfig.X_EXCHANGE,TtlQueueConfig.ROUTING_A,"消息来自ttl为10s的队列"+message);
        rabbitTemplate.convertAndSend(TtlQueueConfig.X_EXCHANGE,TtlQueueConfig.ROUTING_B,"消息来自ttl为40s的队列"+message);
    }

    //开始发消息
    @GetMapping("/sendTtlMsg")
    @ApiOperation("发送消息——》延时时间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message",value = "消息内容"),
            @ApiImplicitParam(name = "ttlTime",value = "延迟时间")
         }
    )
    public void sendMsg(String message,String ttlTime){
        log.info("当前时间发送：{}，发送一条延迟{}的消息给TTL队列:{}",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),ttlTime,message);
        rabbitTemplate.convertAndSend(TtlQueueConfig.X_EXCHANGE,TtlQueueConfig.ROUTING_C,message,msg ->{
            msg.getMessageProperties().setExpiration(ttlTime);
            return msg;
        });
    }


    @GetMapping("/sendDelayMsg")
    @ApiOperation("发送消息——》基于插件延时")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message",value = "消息内容"),
            @ApiImplicitParam(name = "delayTime",value = "延迟时间")
    }
    )
    public void sendDelayMsg(String message,Integer delayTime){
        log.info("当前时间发送：{}，发送一条延迟{}的消息给延迟队列:{}",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),delayTime,message);
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE,DelayedQueueConfig.DELAYED_ROUTING_KEY,message, msg ->{
            msg.getMessageProperties().setDelay(delayTime);
            return msg;
        });
    }
}
