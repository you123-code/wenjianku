package com.demo.designpatterns.nullobjectpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/9 11:37
 */
public abstract class AbstractCustomer {
    protected String name;
    public abstract boolean  isNil();
    public abstract String getName();
}
