package com.demo.designpatterns.proxypattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/28 11:34
 */
public class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;
    public ProxyImage(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void display() {
        if(realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
