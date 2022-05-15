package com.demo.designpatterns.visitorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/10 16:33
 */
public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}
