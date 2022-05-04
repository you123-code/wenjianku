package com.demo.designpatterns.mediatorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/29 13:54
 * 中介者模式
 */
public class MediatorPatternDemo {
    public static void main(String[] args) {
        User robert = new User("Robert");
        User john = new User("John");

        robert.sendMessage("Hi! John!");
        john.sendMessage("Hello! Robert!");
        robert.sendMessage("Are you ok?");
        john.sendMessage("Yes,I'm ok");
    }
}
