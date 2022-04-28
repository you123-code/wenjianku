package com.demo.designpatterns.chainofresponsibilitypattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/28 15:07
 */
public class FileLogger extends AbstractLogger {
    public FileLogger(int level){
        this.level = level;
    }
    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
