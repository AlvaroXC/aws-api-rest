package com.uady.aws_rest_project.validation;

import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class ProfesorValidation {

    private Integer id;

    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 2, message = "El tamaño del nombre debe ser de al menos 2 caracteres")
    private String nombres;

    @NotNull(message = "El apellido es obligatorio")
    @Size(min = 2, message = "El tamaño del apellido debe ser de al menos 2 caracteres")
    private String apellidos;

    @NotNull (message = "El número de empleado es obligatorio")
    @Positive (message = "El número de empleado debe ser un número positivo")
    private Integer numeroEmpleado;

    @NotNull (message = "Las horas de clase son obligatorias")
    @Positive (message = "Las horas de clase deben ser un número entero positivo")
    private Integer horasClase;
}
