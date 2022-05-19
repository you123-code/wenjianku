package com.demo.designpatterns.dataaccessobjectpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/19 23:29
 * 数据访问对象模式
 */
public class DaoPatternDemo {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDaoImpl();
        for (Student student : studentDao.getAllStudent()) {
            System.out.println("Student: [RollNo : "
                    +student.getRollNo()+", Name : "+student.getName()+" ]");
        }
        Student student = studentDao.getAllStudent().get(0);
        student.setName("youwei");
        studentDao.updateStudent(student);

        studentDao.getStudent(0);
        System.out.println("Student: [RollNo : "
                +student.getRollNo()+", Name : "+student.getName()+" ]");
    }
}
