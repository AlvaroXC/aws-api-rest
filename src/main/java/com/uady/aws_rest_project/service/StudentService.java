package com.uady.aws_rest_project.service;

import com.uady.aws_rest_project.model.Student;
import com.uady.aws_rest_project.validation.StudentValidation;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    Student findById(Integer id);

    Student save(StudentValidation studentValidation);
    Student update(Integer id, StudentValidation studentValidation);

    boolean delete(Integer id);

}
