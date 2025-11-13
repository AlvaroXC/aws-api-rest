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
public class ProfesorController {
    private final ProfesorService ProfesorService;

    @Autowired
    public ProfesorController(ProfesorService ProfesorService) {
        this.ProfesorService = ProfesorService;
    }

    @GetMapping
    public ResponseEntity<List<Profesor>> getAll() {
        return ResponseEntity.ok(ProfesorService.getAll());
    }

    @PostMapping
    public ResponseEntity<Profesor> save(@RequestBody @Valid ProfesorValidation ProfesorValidation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ProfesorService.save(ProfesorValidation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> getProfesor(@PathVariable Integer id) {
        Profesor Profesor = ProfesorService.findById(id);
        return Profesor != null
                ? ResponseEntity.ok(Profesor)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> update(@PathVariable Integer id, @Valid @RequestBody ProfesorValidation ProfesorValidation) {
        Profesor ProfesorUpdated = ProfesorService.update(id, ProfesorValidation);
        return ProfesorUpdated != null
                ? ResponseEntity.ok(ProfesorUpdated)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = ProfesorService.delete(id);
        return deleted
                ? ResponseEntity.ok().build()
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
