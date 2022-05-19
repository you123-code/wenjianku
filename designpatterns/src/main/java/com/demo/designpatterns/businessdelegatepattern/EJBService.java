package com.demo.designpatterns.businessdelegatepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/17 15:40
 */
public class EJBService  implements BusinessService {
    @Override
    public void doProcessing() {
        System.out.println("Processing task by invoking EJB Service");
    }
}
