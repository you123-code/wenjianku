package com.demo.designpatterns.builderpattern.cla;

import com.demo.designpatterns.builderpattern.inter.Packing;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/22 13:40
 */
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}
