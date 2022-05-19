package com.demo.designpatterns.businessdelegatepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/17 16:54
 */
public class Client {
    BusinessDelegate businessService;
    public Client(BusinessDelegate businessService){
        this.businessService = businessService;
    }
    public void doTask(){
        businessService.doTask();
    }
}
