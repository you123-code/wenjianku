package com.demo.designpatterns.strategypattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/9 20:52
 */
public class OperationMultiply implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1*num2;
    }
}
