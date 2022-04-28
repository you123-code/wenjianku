package com.demo.designpatterns.commandpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/28 17:07
 */
public class Stock {
    private  String name = "ABC";
    private int quantity =  10;

    public void bug(){
        System.out.println("Stock [ Name: "+name+", Quantity: " + quantity +" ] bought");
    }
    public void sell(){
        System.out.println("Stock [ Name: "+name+", Quantity: " + quantity +" ] sold");
    }
}
