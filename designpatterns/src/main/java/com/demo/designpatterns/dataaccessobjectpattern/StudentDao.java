package com.demo.designpatterns.dataaccessobjectpattern;

import java.util.List;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/19 23:22
 */
public interface StudentDao {
    public List<Student> getAllStudent();
    public Student getStudent(int rollNo);
    public void updateStudent(Student student);
    public void deleteStudent(Student student);
}
