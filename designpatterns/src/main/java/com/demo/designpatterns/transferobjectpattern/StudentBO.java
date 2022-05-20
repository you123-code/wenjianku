package com.demo.designpatterns.transferobjectpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/20 23:31
 */
public class StudentBO {
    List<StudentVO> students;
    public StudentBO(){
        students = new ArrayList<StudentVO>();
        StudentVO student1 = new StudentVO("Robert",0);
        StudentVO student2 = new StudentVO("John",1);
        students.add(student1);
        students.add(student2);
    }

    public void deleteStudent(StudentVO student){
        students.remove(student.getRollNo());
        System.out.println("Student: Roll No "
                + student.getRollNo() +", deleted from database");
    }

    public List<StudentVO> getAllStudents(){
        return students;
    }

    public StudentVO getStudent(int rollNo){
        return students.get(rollNo);
    }

    public void updateStudent(StudentVO studentVO){
        students.get(studentVO.getRollNo()).setName(studentVO.getName());
        System.out.println("Student: Roll No "
                + studentVO.getRollNo() +", updated in the database");
    }
}
