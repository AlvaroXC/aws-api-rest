package com.uady.aws_rest_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="nombres")
    private String nombres;

    @Column(name="apellidos")
    private String apellidos;

    @Column(name="matricula")
    private String matricula;

    @Column(name="promedio")
    private Double promedio;

    @Column(name = "foto_perfil_url")
    private String fotoPerfilUrl;

    @Column(name = "password")
    private String password;

    public Integer getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getMatricula() {
        return matricula;
    }

    public Double getPromedio() {
        return promedio;
    }

    public String getFotoPerfilUrl() {
        return fotoPerfilUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    public void setFotoPerfilUrl(String fotoPerfilUrl) {
        this.fotoPerfilUrl = fotoPerfilUrl;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", matricula='" + matricula + '\'' +
                ", promedio=" + promedio +
                '}';
    }
}
