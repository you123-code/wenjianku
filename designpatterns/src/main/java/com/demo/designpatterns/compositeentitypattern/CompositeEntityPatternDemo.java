package com.demo.designpatterns.compositeentitypattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/19 15:54
 * 组合实体模式
 */
public class CompositeEntityPatternDemo {
    public static void main(String[] args) {
        Client client = new Client();
        client.setData("test","data");
        client.printData();
        client.setData("Second Test", "Data1");
        client.printData();
    }
}
