package com.uady.aws_rest_project.service;

import com.uady.aws_rest_project.model.Student;
import com.uady.aws_rest_project.repository.StudentRepository;
import com.uady.aws_rest_project.validation.StudentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final S3Service s3Service;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, S3Service s3Service){
        this.studentRepository = studentRepository;
        this.s3Service = s3Service;
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
        student.setPassword(studentValidation.getPassword());

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
        theStudent.setPassword(studentValidation.getPassword());

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

    @Override
    public String updatePhoto(Integer id, MultipartFile multipartFile){
        Optional<Student> result = studentRepository.findById(id);

        Student theStudent = null;

        if(result.isEmpty()){
            return null;
        }

        theStudent = result.get();

        try{
            String photoUrl = s3Service.uploadFile(multipartFile, id);
            theStudent.setFotoPerfilUrl(photoUrl);
            studentRepository.save(theStudent);

            return photoUrl;
        }catch (IOException e){
            throw new RuntimeException("Error al subir la imagen", e);
        }

    }
}