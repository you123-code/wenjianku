package com.demo.designpatterns.observerpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/6 10:28
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
