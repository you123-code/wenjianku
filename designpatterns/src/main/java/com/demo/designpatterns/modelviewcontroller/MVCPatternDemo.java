package com.demo.designpatterns.modelviewcontroller;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/15 23:22
 * mvc模式
 */
public class MVCPatternDemo {
    public static void main(String[] args) {
        Student model = retrieveStudentFromDatabase();
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model,view);
        controller.updateView();
        controller.setStudentName("john");
        controller.updateView();
    }

    public static Student retrieveStudentFromDatabase(){
        Student student = new Student();
        student.setName("Robert");
        student.setRollNo("10");
        return student;
    }
}
