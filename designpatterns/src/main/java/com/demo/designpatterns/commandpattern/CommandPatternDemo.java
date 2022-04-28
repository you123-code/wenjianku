package com.demo.designpatterns.commandpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/28 17:22
 * 命令模式
 */
public class CommandPatternDemo {
    public static void main(String[] args) {
        Stock abcStock = new Stock();
        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);
        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);
        broker.placeOrders();
    }
}
