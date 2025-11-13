package com.uady.aws_rest_project.repository;

import com.uady.aws_rest_project.model.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAll();
    Student findById(Integer id);
    Student save(Student student);
    Student update(Integer id, Student student);
    boolean deleteById(Integer id);
}
