package com.uady.aws_rest_project.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Integer id;
    private String nombres;
    private String apellidos;
    private String matricula;
    private Double promedio;


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
