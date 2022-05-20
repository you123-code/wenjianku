package com.demo.designpatterns.transferobjectpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/20 23:40
 * 传输对象模式
 */
public class TransferObjectPatternDemo {
    public static void main(String[] args) {
        StudentBO studentBO = new StudentBO();
        for (StudentVO student : studentBO.getAllStudents()) {
            System.out.println("Student: [RollNo : "
                    +student.getRollNo()+", Name : "+student.getName()+" ]");
        }

        StudentVO studentVO = studentBO.getAllStudents().get(0);
        studentVO.setName("youwei");
        studentBO.updateStudent(studentVO);

        StudentVO student = studentBO.getStudent(0);
        System.out.println("Student: [RollNo : "
                +student.getRollNo()+", Name : "+student.getName()+" ]");
    }
}
