package com.demo.designpatterns.proxypattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/28 11:36
 * 代理模式
 */
public class ProxyPatternDemo {
    public static void main(String[] args) {
        Image image = new ProxyImage("oouuu.jpg");
        image.display();
        System.out.println("ououou");
        image.display();
        image.display();
        ProxyImage proxyImage = new ProxyImage("ououo");
        proxyImage.display();
        System.out.println("ouoou111");
        proxyImage.display();
        proxyImage.display();
    }
}
