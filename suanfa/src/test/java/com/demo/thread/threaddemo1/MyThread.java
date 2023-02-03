package com.demo.thread.threaddemo1;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/1/27 19:08
 */
public class MyThread extends Thread {
    //构造方法
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for(int i = 0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+"正在执行！"+i);
        }
    }
}
