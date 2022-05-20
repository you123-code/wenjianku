package com.demo.designpatterns.interceptingfilterpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/20 11:06
 */
public class Target {
    public void execute(String request){
        System.out.println("Executing request: " + request);
    }
}
