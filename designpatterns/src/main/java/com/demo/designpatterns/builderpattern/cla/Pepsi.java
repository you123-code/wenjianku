package com.demo.designpatterns.builderpattern.cla;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/22 14:01
 */
public class Pepsi extends ColdDrink{
    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 35.0f;
    }
}
