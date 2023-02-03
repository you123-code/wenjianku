package com.demo.thread.threadpooldemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/1/28 6:50
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(2);
        MyCallable callable = new MyCallable();
        service.submit(callable);
        service.submit(callable);
        service.submit(callable);
        service.shutdown();
    }
}
