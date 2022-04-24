package com.demo.designpatterns.builderpattern.inter;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/22 11:49
 */
public interface Item {
    public String name();
    public Packing packing();
    public float price();
}
