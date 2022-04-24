package com.demo.designpatterns.builderpattern;

import com.demo.designpatterns.builderpattern.cla.MealBuilder;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/22 14:44
 */
public class BuilderPatternDemo {
    public static void main(String[] args) {
        MealBuilder m  = MealBuilder.build().addMeal("coke").addMeal("coke").addMeal("coke");
        System.out.println(m.getMeal().getCost());
    }
}
