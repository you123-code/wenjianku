package com.demo.designpatterns.builderpattern.cla;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/22 13:59
 */
public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 30.5f;
    }
}
