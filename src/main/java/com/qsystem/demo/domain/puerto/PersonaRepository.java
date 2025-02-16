package com.qsystem.demo.domain.puerto;

import com.qsystem.demo.domain.model.Persona;

/*
Contrato/Interfaz que sirve para que la capa infrastructure 
se conecte a la capa modelo
*/
public interface PersonaRepository {
	String savePersona (Persona persona) throws Exception;
	Iterable<Persona> getPersonas(String id) throws Exception;	
	Iterable<Persona> getListOrder(Iterable<Persona> listPersona,Integer order);
	Persona getPersona(String dni) throws Exception;
}
