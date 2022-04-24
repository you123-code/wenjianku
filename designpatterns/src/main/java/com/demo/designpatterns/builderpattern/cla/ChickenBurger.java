package com.demo.designpatterns.builderpattern.cla;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/22 13:51
 */
public class ChickenBurger extends Burger {

    @Override
    public String name() {
        return "Chicken  Burger";
    }

    @Override
    public float price() {
        return 20.1f;
    }
}
