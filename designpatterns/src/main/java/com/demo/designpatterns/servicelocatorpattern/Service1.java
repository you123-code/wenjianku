package com.demo.designpatterns.servicelocatorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/20 15:09
 */
public class Service1 implements Service{
    @Override
    public String getName() {
        return "Service1";
    }

    @Override
    public void execute() {
        System.out.println("Executing Service1");
    }
}
