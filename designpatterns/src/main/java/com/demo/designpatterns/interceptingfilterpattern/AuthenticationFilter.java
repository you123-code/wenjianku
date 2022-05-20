package com.demo.designpatterns.interceptingfilterpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/20 11:04
 */
public class AuthenticationFilter implements Filter {
    @Override
    public void execute(String request) {
        System.out.println("Authenticating request: " + request);
    }
}
