package com.qsystem.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.cloud.firestore.DocumentReference;
import lombok.Data;
import lombok.Getter;

@Data
public class Alumno extends Base{
    private String nombres;
    private String apellidos;
    private Integer tipo_documento;
    private String numero_documento;
    private String codigo_estudiante;
    private String grado;
    private String direccion;

    private DocumentReference apoderado;
    private DocumentReference colegioProcedencia;




    // Getter para la referencia como String
    @JsonProperty("apoderado")
    public String getApoderadoId() {
        if (apoderado != null) {
            return apoderado.getId();  // Devuelve el ID del apoderado
        }
        return null;
    }

    @JsonProperty("colegioProcedencia")
    public String getColegioProcedenciaId() {
        if (colegioProcedencia != null) {
            return colegioProcedencia.getId();  // Devuelve el ID del colegio
        }
        return null;
    }
}
