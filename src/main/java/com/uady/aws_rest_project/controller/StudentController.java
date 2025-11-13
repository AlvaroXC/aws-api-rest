package com.uady.aws_rest_project.controller;

import com.uady.aws_rest_project.model.Student;
import com.uady.aws_rest_project.service.StudentService;
import com.uady.aws_rest_project.validation.StudentValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class StudentController {


    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService ){
        this.studentService = studentService;
    }
    
    @GetMapping
    public ResponseEntity<List<Student>> getAll(){
        return ResponseEntity.ok(studentService.getAll());
    }

    @PostMapping
    public ResponseEntity<Student> save (@RequestBody @Valid StudentValidation studentValidation){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(studentValidation));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id){
        Student student = studentService.findById(id);
        return student != null
                ? ResponseEntity.ok(student)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Integer id, @Valid @RequestBody StudentValidation studentValidation){
        Student studentUpdated = studentService.update(id, studentValidation);
        return studentUpdated != null
                ? ResponseEntity.ok(studentUpdated)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        boolean deleted = studentService.delete(id);
        return deleted
                ? ResponseEntity.ok().build()
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}