package com.demo.designpatterns.visitorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/10 16:36
 */
public interface ComputerPartVisitor {
    public void visit(Computer computer);
    public void visit(Mouse mouse);
    public void visit(Keyboard keyboard);
    public void visit(Monitor monitor);
}
