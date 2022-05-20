package com.demo.designpatterns.interceptingfilterpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/20 11:05
 */
public class DebugFilter implements Filter{
    @Override
    public void execute(String request) {
        System.out.println("request log: " + request);
    }
}
