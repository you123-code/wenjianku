package com.demo.suanfa.testbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/1/31 11:44
 */
public class TestBean {
    public static void main(String[] args) {
        System.out.println("开始");
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        //基于注解
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("包路径");
        System.out.println("结束");

    }
}
