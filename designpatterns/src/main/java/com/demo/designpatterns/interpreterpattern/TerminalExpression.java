package com.demo.designpatterns.interpreterpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/28 20:13
 */
public class TerminalExpression implements Expression {
    private String data;
    public TerminalExpression(String data){
        this.data = data;
    }
    @Override
    public boolean interpret(String context) {
        if(context.contains(data)){
            return true;
        }
        return false;
    }
}
