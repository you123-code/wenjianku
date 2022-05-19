package com.demo.designpatterns.dataaccessobjectpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/19 23:17
 */
public class Student {
    private String name;
    private int rollNo;
    Student(String name,int rollNo){
        this.name = name;
        this.rollNo = rollNo;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setRollNo(int rollNo){
        this.rollNo = rollNo;
    }

    public int getRollNo(){
        return rollNo;
    }
}
