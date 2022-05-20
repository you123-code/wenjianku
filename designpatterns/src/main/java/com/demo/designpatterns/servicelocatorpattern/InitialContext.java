package com.demo.designpatterns.servicelocatorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/20 15:14
 */
public class InitialContext {
    public Object lookup(String jndiName){
        if(jndiName.equalsIgnoreCase("service1")){
            System.out.println("Looking up and creating a new Service1 object");
            return new Service1();
        }else if(jndiName.equalsIgnoreCase("service2")){
            System.out.println("Looking up and creating a new Service2 object");
            return new Service2();
        }
        return null;
    }
}
