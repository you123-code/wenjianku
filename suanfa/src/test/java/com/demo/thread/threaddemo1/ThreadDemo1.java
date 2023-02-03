package com.demo.thread.threaddemo1;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/1/27 19:07
 */
public class ThreadDemo1 {
    public static void  main(String[] args){
        long start = System.currentTimeMillis();
        MyThread m1 = new MyThread("新的线程1");
        MyThread m2 = new MyThread("新的线程2");
        m1.start();
        m2.start();
        //for(int i = 0;i<1000;i++){
        //    System.out.println("正在执行1！"+i);
        //}
        //for(int i = 0;i<1000;i++){
        //    System.out.println("正在执行2！"+i);
        //}
        new Thread(){
            public void run(){
                for(int i = 0;i<10;i++){
                    System.out.println("匿名内部类创建线程"+i);
                }
            }
        }.start();

        Runnable runnable = new Runnable() {
            public void run() {
                for(int i = 0;i<10;i++){
                    System.out.println("实现Runnable接口，重新Runnable接口中的run方法,匿名内部类创建线程"+i);
                }
            }
        };
        new Thread(runnable).start();
        //主方法中执行for循环
        for(int i = 0;i<10;i++){
            System.out.println("main线程！"+i);
        }
        System.out.println("结束！！");
        long end = System.currentTimeMillis();
        System.out.println("执行好事："+(end-start));
    }
}
