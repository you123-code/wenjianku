package com.demo.thread.threadpooldemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/1/27 21:41
 */
public class ThreadPoolDemo {
    public static  void main(String[] args){
        //创建线程池
        ExecutorService service = Executors.newFixedThreadPool(2);
        MyRunnable r = new MyRunnable();
        //Thread t = new Thread(r);
        //t.start();
        System.out.println("第一次提交");
        service.submit(r);
        System.out.println("第二次提交");
        service.submit(r);
        System.out.println("第三次提交");
        service.submit(r);
        service.shutdown();
    }
}

