package com.you.springbootrabbitmq.controller;

import com.you.springbootrabbitmq.config.ConfirmConfig;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/9 11:47
 * 发消息测试确认
 */
@Slf4j
@RestController
@RequestMapping("/confirm")
public class ProducerController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage")
    @ApiOperation("测试确认机制")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message",value = "发送的消息")
    })
    public void sendMessage(String message){

        CorrelationData correlationData1 = new CorrelationData(String.valueOf(Math.floor(Math.random()*100)));
        log.info("发送一条消息id为：{}",correlationData1.getId());
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE,ConfirmConfig.CONFIRM_ROUTING,message,correlationData1);
        log.info("发送的消息为：{}",message);

        CorrelationData correlationData = new CorrelationData(String.valueOf(Math.floor(Math.random()*100)));
        log.info("发送一条消息id为：{}",correlationData.getId());
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE,ConfirmConfig.CONFIRM_ROUTING+"12",message,correlationData);
        log.info("发送的消息为：{}",message);
    }
}
