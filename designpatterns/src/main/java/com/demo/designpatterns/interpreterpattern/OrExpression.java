package com.demo.designpatterns.interpreterpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/28 20:16
 */
public class OrExpression implements Expression{
    private Expression expre1 = null;
    private Expression exper2 = null;
    public OrExpression(Expression expre1,Expression exper2){
        this.expre1 = expre1;
        this.exper2 = exper2;
    }
    @Override
    public boolean interpret(String context) {
        return expre1.interpret(context) || exper2.interpret(context);
    }
}
