package com.qsystem.demo.domain.model;

import lombok.Data;

@Data
public class Apoderado {
    private String nombres;
    private String apellidos;
    private Integer tipo_documento;
    private String numero_documento;
    private String celular;
    private String relacion;
}
