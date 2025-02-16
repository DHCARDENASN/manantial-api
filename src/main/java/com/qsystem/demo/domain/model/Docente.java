package com.qsystem.demo.domain.model;

import lombok.Data;

@Data
public class Docente extends Base{
    private String nombres;
    private String apellidos;
    private Integer tipo_documento;
    private String numero_documento;
    private String grado;
    private String seccion;
}
