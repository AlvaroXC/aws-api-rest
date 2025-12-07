package com.uady.aws_rest_project.service;

import com.uady.aws_rest_project.model.Profesor;
import com.uady.aws_rest_project.repository.ProfesorRepository;
import com.uady.aws_rest_project.validation.ProfesorValidation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServiceImpl implements ProfesorService{
    private final ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorServiceImpl(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    @Override
    public List<Profesor> getAll() {
        return profesorRepository.findAll();
    }

    @Override
    public Profesor findById(Integer id) {

        Optional<Profesor> result = profesorRepository.findById(id);
        Profesor theProfesor = null;

        if(result.isPresent()){
            theProfesor = result.get();
        }

        return theProfesor;
    }

    @Override
    @Transactional
    public Profesor save(ProfesorValidation profesorValidation) {
        Profesor profesor = new Profesor();
        profesor.setNombres(profesorValidation.getNombres());
        profesor.setApellidos(profesorValidation.getApellidos());
        profesor.setNumeroEmpleado(profesorValidation.getNumeroEmpleado());
        profesor.setHorasClase(profesorValidation.getHorasClase());

        return profesorRepository.save(profesor);
    }

    @Override
    public Profesor update(Integer id, ProfesorValidation profesorValidation) {
        Optional<Profesor> result = profesorRepository.findById(id);
        Profesor theProfesor = null;

        if(result.isEmpty()){
            return null;
        }

        theProfesor = result.get();

        theProfesor.setNombres(profesorValidation.getNombres());
        theProfesor.setApellidos(profesorValidation.getApellidos());
        theProfesor.setNumeroEmpleado(profesorValidation.getNumeroEmpleado());
        theProfesor.setHorasClase(profesorValidation.getHorasClase());

        return profesorRepository.save(theProfesor);
    }

    @Override
    public boolean delete(Integer id) {

        Optional<Profesor> result = profesorRepository.findById(id);
        Profesor theProfesor = null;

        if(result.isEmpty()){
            return false;
        }

        theProfesor = result.get();
        profesorRepository.deleteById(theProfesor.getId());

        return true;
    }
}
