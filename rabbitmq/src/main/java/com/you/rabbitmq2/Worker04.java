package com.you.rabbitmq2;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.you.util.RabbitMqUtil;
import com.you.util.SleepUtils;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/7/25 14:28
 * 消费者，消息在手动应答时是不丢失的，放回消息队列中重新消费
 */
public class Worker04 {
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception{
        System.out.println("C2等待消息处理时间较长！！！");
        Channel channel = RabbitMqUtil.getChannel();
        DeliverCallback deliverCallback = (vlu1,vlu2) ->{
            //沉睡一秒，模拟业务处理时间
            SleepUtils.sleep(2);
            System.out.println("接收到的消息---->"+  new String(vlu2.getBody(),"UTF-8"));
            /**
             * 手动应答
             * 1.消息的标记tag
             * 2.是否批量应答，false：不批量，
             */
            channel.basicAck(vlu2.getEnvelope().getDeliveryTag(),false);
        };
        //取消消息的回调
        CancelCallback cancelCallback = var1 ->{
            System.out.println("消费被中断触发回调！！");
        };
        channel.basicConsume(TASK_QUEUE_NAME,false,deliverCallback,cancelCallback);
    }
}
