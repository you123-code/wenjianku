package com.demo.designpatterns.nullobjectpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/9 11:44
 */
public class RealCustomer extends AbstractCustomer {
    public RealCustomer(String name) {
        this.name = name;
    }

    @Override
    public boolean isNil() {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
