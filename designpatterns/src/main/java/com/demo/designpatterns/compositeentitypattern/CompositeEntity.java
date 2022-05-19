package com.demo.designpatterns.compositeentitypattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/19 15:48
 */
public class CompositeEntity {
    private CoarseGrainedObject cgo = new CoarseGrainedObject();

    public void setData(String data1,String data2){
        cgo.setData(data1,data2);
    }

    public String[] getData(){
        return  cgo.getData();
    }
}
