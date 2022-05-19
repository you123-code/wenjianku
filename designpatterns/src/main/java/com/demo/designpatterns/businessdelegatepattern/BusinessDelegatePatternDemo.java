package com.demo.designpatterns.businessdelegatepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/17 16:56
 * 业务代表模式
 */
public class BusinessDelegatePatternDemo {
    public static void main(String[] args) {
        BusinessDelegate businessDelegate = new BusinessDelegate();
        businessDelegate.setServiceType("ejb");
        Client client =  new Client(businessDelegate);
        client.doTask();
        businessDelegate.setServiceType("jms");
        client.doTask();
    }
}
