package com.you.rabbitmq3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.you.util.RabbitMqUtil;

import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/7/25 16:12
 * 发布确认模式
 *      测试使用的时间，判断哪一种确认方式最好
 * 1.单个确认模式
 * 2.批量确认模式
 * 3.异步批量确认模式
 */
public class ConfirmMessage {
    //批量发消息的个数
    public static final int MESSAGE_COUNT = 100;
    public static void main(String[] args) throws Exception {
        //1.单个确认
        //ConfirmMessage.publishMessageIndividually();
        //2.批量确认
        //ConfirmMessage.publishMessageBatch();
        //3.异步批量确认
        ConfirmMessage.publishMessageAsync();
    }

    //单个发布确认100个数据，用时---》185ms
    //批量发布确认100个数据，用时---》123ms
    //  异步发布确认100个数据，用时---》9ms
    //单个确认
    public static void publishMessageIndividually()  throws Exception{
        Channel channel = RabbitMqUtil.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,false,false,false,null);
        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long beginTime = System.currentTimeMillis();
        //批量发信息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("",queueName,null,message.getBytes());
            //单个消息马上进行发布确认
            boolean flag = channel.waitForConfirms();
            if(flag){
                System.out.println("消息发送成功");
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("单个发布确认"+MESSAGE_COUNT+"个数据，用时---》"+(endTime - beginTime)+"ms");
    }
    //批量确认
    public static void publishMessageBatch() throws Exception{
        Channel channel = RabbitMqUtil.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,false,false,false,null);
        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long beginTime = System.currentTimeMillis();
        //批次大小
        int batchSize = 10;
        //批量发信息
        for (int i = 1; i < MESSAGE_COUNT+1; i++) {
            String message = i + "";
            channel.basicPublish("",queueName,null,message.getBytes());
            //达到批次马上进行发布确认
                if(i%batchSize == 0){
                    boolean flag = channel.waitForConfirms();
                    if(flag){
                        System.out.println("第"+i+"条消息发送成功");
                    }
                }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("批量发布确认"+MESSAGE_COUNT+"个数据，用时---》"+(endTime - beginTime)+"ms");
    }
    //异步批量确认
    public static void publishMessageAsync() throws Exception{
        Channel channel = RabbitMqUtil.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,false,false,false,null);
        //开启发布确认
        channel.confirmSelect();

        /**
         * 线程安全有序的一个哈希表，适用于高并发的情况下
         * 1.轻松地将序号与消息进行关联
         * 2.轻松批量删除条目，只要给到序号
         * 3.支持高并发（多线程）
         */
        ConcurrentSkipListMap<Long,String> outstandingConfirms = new ConcurrentSkipListMap<>();

        //准备消息的监听器，那些消息成功了，那些消息失败了
        //监听成功的
        ConfirmCallback ackCallback = (vlu1,vlu3) ->{
            if(vlu3){
                //2.删除已经确认的数据，剩下的都是未确认
                ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(vlu1);
                confirmed.clear();
            }else{
                outstandingConfirms.remove(vlu1);
            }
            System.out.println("确认的消息----》"+vlu1);
        };
        //监听失败的
        /**
         * 1.消息的标记
         * 2.是否为批量确认
         */
        ConfirmCallback nackCallback = (vlu1,vlu2) ->{
            //3、打印未确认的消息有哪些
            String message = outstandingConfirms.get(vlu1);
            System.out.println("未确认的消息----》"+message+"::::未确认的消息tag----》"+vlu1);
        };
        //异步监听
        /**
         * 1.监听那些消息成功了
         * 2.监听那些消息失败了
         */
        channel.addConfirmListener(ackCallback,nackCallback);
        //开始时间
        long beginTime = System.currentTimeMillis();
        //批量发送消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("",queueName,null,message.getBytes());
            //1.此处记录下所有要发送的信息，消息的总和
            outstandingConfirms.put(channel.getNextPublishSeqNo(),message);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("异步发布确认"+MESSAGE_COUNT+"个数据，用时---》"+(endTime - beginTime)+"ms");
    }
}
