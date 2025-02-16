package com.qsystem.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Base {
    // Campos de auditoría
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fecha_creacion;  // Fecha de creación

    private String usuario_creacion;  // Usuario que creó el registro

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fecha_modificacion;  // Fecha de modificación

    private String usuario_modificacion;
}
