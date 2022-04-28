package com.demo.designpatterns.proxypattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/28 11:29
 */
public class RealImage implements Image {
    private String fileName;
    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk(fileName);
    }
    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }
    public void loadFromDisk(String fileName){
        System.out.println("Loading " + fileName);
    }
}
