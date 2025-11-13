package com.uady.aws_rest_project.repository;

import com.uady.aws_rest_project.model.Student;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class StudentRepositoryImpl implements StudentRepository{

    private final Map<Integer, Student> students = new HashMap<>();

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student findById(Integer id) {
        return students.get(id);
    }

    @Override
    public Student save(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student update(Integer id, Student student) {
        student.setId(id);
        students.put(id, student);
        return student;
    }

    @Override
    public boolean deleteById(Integer id) {
        return students.remove(id) != null;
    }
}