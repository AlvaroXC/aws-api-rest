package com.uady.aws_rest_project.service;

import com.uady.aws_rest_project.model.Student;
import com.uady.aws_rest_project.repository.StudentRepository;
import com.uady.aws_rest_project.validation.StudentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Student> result = studentRepository.findById(id);
        Student theStudent = null;

        if(result.isPresent()){
            theStudent = result.get();
        }

        return theStudent;

    }

    @Override
    public Student save(StudentValidation studentValidation) {
        Student student = new Student();
        student.setNombres(studentValidation.getNombres());
        student.setApellidos(studentValidation.getApellidos());
        student.setMatricula(studentValidation.getMatricula());
        student.setPromedio(studentValidation.getPromedio());

        return studentRepository.save(student);
    }

    @Override
    public Student update(Integer id, StudentValidation studentValidation) {
        Optional<Student> result = studentRepository.findById(id);

        Student theStudent = null;

        if(result.isEmpty()){
            return null;
        }

        theStudent = result.get();

        theStudent.setNombres(studentValidation.getNombres());
        theStudent.setApellidos(studentValidation.getApellidos());
        theStudent.setMatricula(studentValidation.getMatricula());
        theStudent.setPromedio(studentValidation.getPromedio());

        return studentRepository.save(theStudent);
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Student> result = studentRepository.findById(id);

        Student theStudent = null;

        if(result.isEmpty()){
            return false;
        }

        theStudent = result.get();
        studentRepository.deleteById(theStudent.getId());

        return true;
    }
}