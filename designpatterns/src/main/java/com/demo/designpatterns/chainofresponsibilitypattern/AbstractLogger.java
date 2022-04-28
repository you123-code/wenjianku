package com.demo.designpatterns.chainofresponsibilitypattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/28 14:09
 */
public abstract class AbstractLogger {
    public static final int INFO = 1;
    public static final int DEBUG = 2;
    public static final int ERROR = 3;

    protected int level;
    //责任链下一个元素
    protected AbstractLogger nextLogger;
    public  void setNextLogger(AbstractLogger nextLogger){
        this.nextLogger = nextLogger;
    }
    public void logMessage(int level,String message){
        if(this.level <= level){
            write(message);
        }
        if(nextLogger !=null){
            nextLogger.logMessage(level,message);
        }
    }
    abstract protected void write(String message);
}

