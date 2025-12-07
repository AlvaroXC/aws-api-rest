package com.uady.aws_rest_project.repository;

import com.uady.aws_rest_project.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Integer> {

}
