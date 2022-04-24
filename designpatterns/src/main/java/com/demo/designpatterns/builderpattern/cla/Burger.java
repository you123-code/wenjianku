package com.demo.designpatterns.builderpattern.cla;

import com.demo.designpatterns.builderpattern.inter.Item;
import com.demo.designpatterns.builderpattern.inter.Packing;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/22 13:42
 */
public  abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }
}
