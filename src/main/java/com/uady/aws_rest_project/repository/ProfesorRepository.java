package com.uady.aws_rest_project.repository;

import com.uady.aws_rest_project.model.Profesor;

import java.util.List;

public interface ProfesorRepository {
    List<Profesor> findAll();
    Profesor findById(Integer id);
    Profesor save(Profesor profesor);
    Profesor update(Integer id, Profesor profesor);
    boolean deleteById(Integer id);
}
