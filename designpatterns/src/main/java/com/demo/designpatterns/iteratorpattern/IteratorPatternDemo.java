package com.demo.designpatterns.iteratorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/29 10:53
 */
public class IteratorPatternDemo {
    public static void main(String[] args) {
        NameRepository nameRepository = new NameRepository();
        for(Iterator iterator = nameRepository.getIterator();iterator.hasNext();){
            String name = (String) iterator.next();
            System.out.println("Name : " + name);
        }
    }
}
