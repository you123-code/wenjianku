package com.demo.designpatterns.strategypattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/9 20:53
 */
public class Context {
    private Strategy strategy;
    public Context(Strategy strategy){
        this.strategy = strategy;
    }
    public int executeStrategy(int num1,int num2){
      return   strategy.doOperation(num1, num2);
    }
}
