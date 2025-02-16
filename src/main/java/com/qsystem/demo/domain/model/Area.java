package com.qsystem.demo.domain.model;


import lombok.Data;

@Data
public class Area extends Base{

    private String nombre;
    private String descripcion;
    private boolean tiene_libro;
}
