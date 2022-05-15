package com.demo.designpatterns.visitorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/10 16:36
 */
public class Computer implements ComputerPart {
    ComputerPart[] parts;
    public Computer(){
        parts = new ComputerPart[]{new Mouse(),new Keyboard(),new Monitor()};
    }
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        for (int i = 0; i < parts.length; i++) {
            parts[i].accept(computerPartVisitor);
        }
        computerPartVisitor.visit(this);
    }
}
