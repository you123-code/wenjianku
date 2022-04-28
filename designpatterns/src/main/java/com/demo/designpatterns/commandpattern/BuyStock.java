package com.demo.designpatterns.commandpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/28 17:09
 */
public class BuyStock implements Order {
    private Stock abcStock;
    public BuyStock(Stock abcStock){
        this.abcStock = abcStock;
    }
    @Override
    public void execute() {
        abcStock.bug();
    }
}
