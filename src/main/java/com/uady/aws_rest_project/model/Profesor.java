package com.uady.aws_rest_project.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {

    private Integer id;
    private Integer numeroEmpleado;
    private String nombres;
    private String apellidos;
    private Integer horasClase;

    @Override
    public String toString() {
        return "Profesor{" +
                "id='" + id + '\'' +
                ", numeroEmpleado=" + numeroEmpleado +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", horasClase=" + horasClase +
                '}';
    }
}

