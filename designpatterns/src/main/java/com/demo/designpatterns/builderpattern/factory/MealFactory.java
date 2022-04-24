package com.demo.designpatterns.builderpattern.factory;

import com.demo.designpatterns.builderpattern.cla.Coke;
import com.demo.designpatterns.builderpattern.cla.Pepsi;
import com.demo.designpatterns.builderpattern.cla.VegBurger;
import com.demo.designpatterns.builderpattern.inter.Item;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/22 15:37
 */
public class MealFactory {
    public Item getItem(String itemType){
        if(itemType == null){
            return null;
        }
        if(itemType.equalsIgnoreCase("coke")){
            return new Coke();
        }else if(itemType.equalsIgnoreCase("pesi")){
            return new Pepsi();
        }else if(itemType.equalsIgnoreCase("chicken")){
            return new Pepsi();
        }else if(itemType.equalsIgnoreCase("vegetable")){
            return new VegBurger();
        }
        return null;
    }
}
