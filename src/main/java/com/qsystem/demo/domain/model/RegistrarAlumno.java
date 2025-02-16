package com.qsystem.demo.domain.model;

import lombok.Data;

@Data
public class RegistrarAlumno extends Base{

    private Alumno alumno;
    private Apoderado apoderado;
    private ColegioProcedencia colegio_procedencia;

}
