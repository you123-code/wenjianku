package com.demo.thread.threaddemo3;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/1/29 14:51
 */
public class ThreadDemo3 {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        Object o2 = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (o1) {
                synchronized (o2) {
                    try {
                        System.out.println("t1获取到锁");
                        Thread.sleep(5000);
                        System.out.println("t1结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(() -> {
            synchronized (o1) {
                synchronized (o2) {
                    try {
                        System.out.println("t2获取到锁");
                        Thread.sleep(5000);
                        System.out.println("t2结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2.start();
        t1.stop();
    }
}
