package com.demo.designpatterns.visitorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/10 16:35
 */
public class Keyboard implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
