package com.demo.thread.threaddemo2;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/1/27 20:56
 */
public class MyRunnable implements Runnable{

    @Override
    public void run() {
        for(int i = 0;i<10;i++){
            System.out.println("自定义线程正在执行："+i);
        }
    }
}
