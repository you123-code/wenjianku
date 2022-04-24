package com.demo.designpatterns.builderpattern.cla;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/22 13:49
 */
public class VegBurger extends Burger{

    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 10.1f;
    }
}
