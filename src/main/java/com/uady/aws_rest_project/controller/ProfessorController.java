package com.uady.aws_rest_project.controller;

import com.uady.aws_rest_project.model.Profesor;
import com.uady.aws_rest_project.service.ProfesorService;
import com.uady.aws_rest_project.validation.ProfesorValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfessorController {
    private final ProfesorService profesorService;

    @Autowired
    public ProfessorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public ResponseEntity<List<Profesor>> getAll() {
        return ResponseEntity.ok(profesorService.getAll());
    }

    @PostMapping
    public ResponseEntity<Profesor> save(@RequestBody @Valid ProfesorValidation profesorValidation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profesorService.save(profesorValidation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> getProfesor(@PathVariable Integer id) {
        Profesor profesor = profesorService.findById(id);
        return profesor != null
                ? ResponseEntity.ok(profesor)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> update(@PathVariable Integer id, @Valid @RequestBody ProfesorValidation profesorValidation) {
        Profesor professorUpdated = profesorService.update(id, profesorValidation);
        return professorUpdated != null
                ? ResponseEntity.ok(professorUpdated)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = profesorService.delete(id);
        return deleted
                ? ResponseEntity.ok().build()
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
