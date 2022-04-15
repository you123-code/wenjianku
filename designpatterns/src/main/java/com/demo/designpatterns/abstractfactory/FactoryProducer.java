package com.demo.designpatterns.abstractfactory;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/14 14:23
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        }else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}
