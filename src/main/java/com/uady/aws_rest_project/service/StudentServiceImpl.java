package com.uady.aws_rest_project.service;

import com.uady.aws_rest_project.model.Student;
import com.uady.aws_rest_project.repository.StudentRepository;
import com.uady.aws_rest_project.validation.StudentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(StudentValidation studentValidation) {
        Student student = new Student();
        student.setId(studentValidation.getId());
        student.setNombres(studentValidation.getNombres());
        student.setApellidos(studentValidation.getApellidos());
        student.setMatricula(studentValidation.getMatricula());
        student.setPromedio(studentValidation.getPromedio());

        return studentRepository.save(student);
    }

    @Override
    public Student update(Integer id, StudentValidation studentValidation) {
        Student existingStudent = studentRepository.findById(id);
        if(existingStudent == null) return null;

        existingStudent.setNombres(studentValidation.getNombres());
        existingStudent.setApellidos(studentValidation.getApellidos());
        existingStudent.setMatricula(studentValidation.getMatricula());
        existingStudent.setPromedio(studentValidation.getPromedio());

        return studentRepository.update(id, existingStudent);
    }

    @Override
    public boolean delete(Integer id) {
        return studentRepository.deleteById(id);
    }
}