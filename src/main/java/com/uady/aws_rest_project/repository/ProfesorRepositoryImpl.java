package com.uady.aws_rest_project.repository;

import com.uady.aws_rest_project.model.Profesor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProfesorRepositoryImpl implements ProfesorRepository{

    private final Map<Integer, Profesor> profesores = new HashMap<>();

    @Override
    public List<Profesor> findAll() {
        return new ArrayList<>(profesores.values());
    }

    @Override
    public Profesor findById(Integer id) {
        return profesores.get(id);
    }

    @Override
    public Profesor save(Profesor profesor) {
        profesores.put(profesor.getId(), profesor);
        return profesor;
    }

    @Override
    public Profesor update(Integer id, Profesor profesor) {
        profesor.setId(id);
        profesores.put(id, profesor);
        return profesor;
    }

    @Override
    public boolean deleteById(Integer id) {
        return profesores.remove(id) != null;
    }
}
