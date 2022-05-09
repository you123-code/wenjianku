package com.demo.designpatterns.nullobjectpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/9 11:58
 */
public class NullCustomer extends AbstractCustomer {
    @Override
    public boolean isNil() {
        return true;
    }

    @Override
    public String getName() {
        return "Not Available in Customer Database";
    }
}
