package com.demo.designpatterns.modelviewcontroller;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/10 17:18
 */
public class StudentController {
    private  Student model;
    private StudentView view;
    public StudentController(Student model,StudentView view){
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name){
        model.setName(name);
    }

    public String getStudentName(){
        return model.getName();
    }

    public void setStudentRollNo(String rollNo){
        model.setRollNo(rollNo);
    }

    public String getStudentRollNo(){
        return  model.getRollNo();
    }

    public void updateView(){
        view.printStudentDetails(model.getName(),model.getRollNo());
    }
}
