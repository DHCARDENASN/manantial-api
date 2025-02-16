package com.qsystem.demo.domain.model;

import lombok.Builder;
import lombok.Data;


public class Persona {
    private String dni;
    private String appPaterno;
    private String appMatterno;
    
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getAppPaterno() {
		return appPaterno;
	}
	public void setAppPaterno(String appPaterno) {
		this.appPaterno = appPaterno;
	}
	public String getAppMatterno() {
		return appMatterno;
	}
	public void setAppMatterno(String appMatterno) {
		this.appMatterno = appMatterno;
	}

    
}