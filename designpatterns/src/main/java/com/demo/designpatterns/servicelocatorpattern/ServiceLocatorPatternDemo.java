package com.demo.designpatterns.servicelocatorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/20 15:36
 * 服务定位器模式
 */
public class ServiceLocatorPatternDemo {
    public static void main(String[] args) {
        Service service = ServiceLocator.getService("Service1");
        service.execute();
        service = ServiceLocator.getService("Service2");
        service.execute();
        service = ServiceLocator.getService("Service1");
        service.execute();
        service = ServiceLocator.getService("Service2");
        service.execute();
    }
}
