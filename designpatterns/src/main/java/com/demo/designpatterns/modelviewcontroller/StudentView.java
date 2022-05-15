package com.demo.designpatterns.modelviewcontroller;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/10 17:15
 */
public class StudentView {
    public void printStudentDetails(String studentName,String studentRollNo){
        System.out.println("Student: ");
        System.out.println("Name: " + studentName);
        System.out.println("Roll No: " + studentRollNo);
    }
}
