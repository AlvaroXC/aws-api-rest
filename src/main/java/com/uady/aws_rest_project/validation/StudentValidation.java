package com.uady.aws_rest_project.validation;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentValidation {

    private Integer id;

    @NotNull(message = "El nombre es obligatorio")
    private String nombres;

    @NotNull(message = "El apellido es obligatorio")
    private String apellidos;

    @NotNull (message = "La matricula es obligatoria")
    private String matricula;

    @NotNull (message = "El promedio es obligatorio")
    @DecimalMin(value = "0.0", message = "El promedio debe ser mayor o igual a 0")
    @DecimalMax(value = "10.0", message = "El promedio no puede ser mayor a 10.0")
    private Double promedio;

}
