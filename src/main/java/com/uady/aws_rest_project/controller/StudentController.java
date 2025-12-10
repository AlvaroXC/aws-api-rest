package com.uady.aws_rest_project.controller;

import com.uady.aws_rest_project.model.Student;
import com.uady.aws_rest_project.service.S3Service;
import com.uady.aws_rest_project.service.SnsService;
import com.uady.aws_rest_project.service.StudentService;
import com.uady.aws_rest_project.validation.StudentValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alumnos")
public class StudentController {


    private final StudentService studentService;
    private final S3Service s3Service;
    private final SnsService snsService;

    @Autowired
    public StudentController(StudentService studentService, S3Service s3Service, SnsService snsService ){
        this.studentService = studentService;
        this.s3Service = s3Service;
        this.snsService = snsService;
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

    @PostMapping("/{id}/fotoPerfil")
    public ResponseEntity<Map<String, String>> uploadFotoPerfil(
            @PathVariable Integer id,
            @RequestParam("foto") MultipartFile file) {


       String url = studentService.updatePhoto(id, file);
        if (url == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Map<String, String> response = new HashMap<>();
        response.put("fotoPerfilUrl", url);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/{id}/email")
    public ResponseEntity<Map<String, String>> sendEmail(@PathVariable Integer id){
        Student student = studentService.findById(id);

        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        snsService.sendGradesNotification(student);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Correo enviado exitosamente");

        return ResponseEntity.ok(response);
    }

}