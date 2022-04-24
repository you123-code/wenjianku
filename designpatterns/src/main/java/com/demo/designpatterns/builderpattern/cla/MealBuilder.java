package com.demo.designpatterns.builderpattern.cla;

import com.demo.designpatterns.builderpattern.factory.MealFactory;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/22 14:38
 */
public class MealBuilder<Record> {
    private Class<Record> type;
    private Meal meal = new Meal();

    public MealBuilder() {
    }

    public static  MealBuilder build(){
        return new MealBuilder();
    }

    public MealBuilder(Class<Record> type) {
    }

    public static <Record> MealBuilder<Record> build(Class<Record> type){
        return new MealBuilder<>(type);
    }

    public  MealBuilder  addMeal(String type){
        MealFactory mealFactory = new MealFactory();
        meal.addItem(mealFactory.getItem(type));
       return this;
    }
    public Meal getMeal(){
        return meal;
    }

}
