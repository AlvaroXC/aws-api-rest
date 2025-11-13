package com.uady.aws_rest_project.service;

import com.uady.aws_rest_project.model.Profesor;
import com.uady.aws_rest_project.validation.ProfesorValidation;

import java.util.List;

public interface ProfesorService {
    List<Profesor> getAll();
    Profesor findById(Integer id);
    Profesor save(ProfesorValidation ProfesorValidation);
    Profesor update(Integer id, ProfesorValidation ProfesorValidation);
    boolean delete(Integer id);
}
