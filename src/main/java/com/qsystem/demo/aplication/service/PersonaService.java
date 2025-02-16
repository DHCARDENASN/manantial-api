package com.qsystem.demo.aplication.service;

import com.qsystem.demo.domain.model.Persona;

public interface PersonaService {
	String savePersona (Persona persona) throws Exception;
	Iterable<Persona> getPersonas(String id)  throws Exception;	
	Iterable<Persona> getListOrder(Iterable<Persona> listPersona,Integer order);
	Persona getPersona(String dni) throws Exception;
}
