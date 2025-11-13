package com.uady.aws_rest_project.service;

import com.uady.aws_rest_project.model.Profesor;
import com.uady.aws_rest_project.repository.ProfesorRepository;
import com.uady.aws_rest_project.validation.ProfesorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService{
    private final ProfesorRepository ProfesorRepository;

    @Autowired
    public ProfesorServiceImpl(ProfesorRepository ProfesorRepository) {
        this.ProfesorRepository = ProfesorRepository;
    }

    @Override
    public List<Profesor> getAll() {
        return ProfesorRepository.findAll();
    }

    @Override
    public Profesor findById(Integer id) {
        return ProfesorRepository.findById(id);
    }

    @Override
    public Profesor save(ProfesorValidation ProfesorValidation) {
        Profesor Profesor = new Profesor();
        Profesor.setId(ProfesorValidation.getId());
        Profesor.setNombres(ProfesorValidation.getNombres());
        Profesor.setApellidos(ProfesorValidation.getApellidos());
        Profesor.setNumeroEmpleado(ProfesorValidation.getNumeroEmpleado());
        Profesor.setHorasClase(ProfesorValidation.getHorasClase());

        return ProfesorRepository.save(Profesor);
    }

    @Override
    public Profesor update(Integer id, ProfesorValidation ProfesorValidation) {
        Profesor existingProfesor = ProfesorRepository.findById(id);
        if (existingProfesor == null) return null;

        existingProfesor.setNombres(ProfesorValidation.getNombres());
        existingProfesor.setApellidos(ProfesorValidation.getApellidos());
        existingProfesor.setNumeroEmpleado(ProfesorValidation.getNumeroEmpleado());
        existingProfesor.setHorasClase(ProfesorValidation.getHorasClase());

        return ProfesorRepository.update(id, existingProfesor);
    }

    @Override
    public boolean delete(Integer id) {
        return ProfesorRepository.deleteById(id);
    }
}
