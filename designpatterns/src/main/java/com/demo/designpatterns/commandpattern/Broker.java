package com.demo.designpatterns.commandpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/28 17:19
 */
public class Broker {
    private List<Order> orderList = new ArrayList<Order>();
    public void takeOrder(Order order){
        orderList.add(order);
    }
    public void placeOrders(){
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
