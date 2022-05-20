package com.demo.designpatterns.transferobjectpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/20 23:27
 */
public class StudentVO {
    private String name;
    private int rollNo;
    StudentVO(String name,int rollNo){
        this.name = name;
        this.rollNo = rollNo;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getRollNo(){
        return rollNo;
    }

    public void setRollNo(int rollNo){
        this.rollNo = rollNo;
    }
}
