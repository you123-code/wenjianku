package com.demo.thread.threadpooltestdemo;

import java.util.concurrent.Callable;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/1/28 7:06
 */
public class MyCallable implements Callable {
    int a = 0,b = 0;

    public MyCallable(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return a+b;
    }
}
