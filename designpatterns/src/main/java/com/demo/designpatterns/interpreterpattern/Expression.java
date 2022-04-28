package com.demo.designpatterns.interpreterpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/28 20:10
 */
public interface Expression {
    public boolean interpret(String context);
}
