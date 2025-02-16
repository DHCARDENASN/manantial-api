package com.qsystem.demo.domain.model;

import lombok.Data;

@Data
public class Competencia extends Base{

    private String nombre;
    private String descripcion;
    private boolean transversal;

}
