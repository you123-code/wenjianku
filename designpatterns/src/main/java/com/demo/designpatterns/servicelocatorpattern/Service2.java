package com.demo.designpatterns.servicelocatorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/20 15:10
 */
public class Service2 implements Service{
    @Override
    public String getName() {
        return "Service2";
    }

    @Override
    public void execute() {
        System.out.println("Executing Service2");
    }
}
