package com.demo.thread.threaddemo4;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/3/7 14:20
 * A线程执行完两次，B线程执行一次，如此执行5次
 */
public class VolatileDemo {

    //定义一个标志位，当标志位更改后执行不同线程
    private static volatile boolean flag =  true;

    public static void main(String[] args) {
        //线程A
        new Thread("A"){
            public void run(){
                int i=0;
                while (true){
                    if(flag){
                        System.out.println(Thread.currentThread().getName()+":"+ ++i);
                    }
                    if(i%2==0){
                        flag = false;
                    }
                    if(i==10) break;
                }
            }
        }.start();

        new Thread("B"){
            public void run(){
                int i=0;
                while (true){
                    if(!flag){
                        System.out.println(Thread.currentThread().getName()+":"+ ++i);
                        flag = true;
                        if(i == 5) break;
                    }
                }
            }
        }.start();
    }

}
