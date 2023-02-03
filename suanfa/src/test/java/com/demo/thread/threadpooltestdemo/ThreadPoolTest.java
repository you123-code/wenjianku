package com.demo.thread.threadpooltestdemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/1/28 7:05
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        MyCallable myCallable = new MyCallable(2,3);
        MyCallable myCallable1 = new MyCallable(5,2);
        Future<Integer> result = executorService.submit(myCallable);
        Future<Integer> result2 = executorService.submit(myCallable1);
        System.out.println("sum1:"+result.get()+"  sum2:"+result2.get());
        executorService.shutdown();
    }
}
