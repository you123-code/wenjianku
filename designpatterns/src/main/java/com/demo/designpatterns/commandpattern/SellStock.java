package com.demo.designpatterns.commandpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/28 17:11
 */
public class SellStock implements Order {
    private Stock abcStock;
    public SellStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.sell();
    }
}
