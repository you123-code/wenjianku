package com.demo.designpatterns.nullobjectpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/9 16:16
 */
public class CustomerFactory {
    public static final String[]  names = {"Rob", "Joe", "Julie"};
    public static AbstractCustomer getCustomer(String name){
        for (int i = 0; i < names.length; i++) {
            if(names[i].equalsIgnoreCase(name)){
                return new RealCustomer(names[i]);
            }
        }
        return new NullCustomer();
    }
}
