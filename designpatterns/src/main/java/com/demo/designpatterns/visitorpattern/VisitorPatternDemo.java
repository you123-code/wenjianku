package com.demo.designpatterns.visitorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/10 16:53
 * 访问者模式
 */
public class VisitorPatternDemo {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
