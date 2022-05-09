package com.demo.designpatterns.mementopattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/5 17:23
 */
public class CareTaker {
    private List<Memento> mementoList  =  new ArrayList<Memento>();

    public void add(Memento state){
        mementoList.add(state);
    }
    public Memento get(int index){
        return mementoList.get(index);
    }
}
