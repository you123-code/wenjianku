package com.demo.designpatterns.compositeentitypattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/19 15:27
 */
public class CoarseGrainedObject {
    DependentObject1 dep1 = new DependentObject1();
    DependentObject2 dep2 = new DependentObject2();

    public void setData(String data1,String data2){
        dep1.setData(data1);
        dep2.setData(data2);
    }

    public String[] getData(){
        return new String[]{dep1.getData(),dep2.getData()};
    }
}
