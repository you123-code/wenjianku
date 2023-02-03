package com.demo.thread.threaddemo2;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/1/27 20:58
 */
public class ThreadDemo2 {
    public static void main(String[] args){
        MyRunnable rn = new MyRunnable();
        Thread t1 = new Thread(rn);
        Thread t2 = new Thread(rn);
        //开启线程
        t1.start();
        t2.start();
        for(int i=0;i<10;i++){
            System.out.println("main线程正在执行！"+i);
        }
        System.out.println("结束！");
    }
}
