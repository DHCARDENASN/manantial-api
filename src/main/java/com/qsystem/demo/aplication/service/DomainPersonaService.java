package com.qsystem.demo.aplication.service;

import com.qsystem.demo.domain.model.Persona;
import com.qsystem.demo.domain.puerto.PersonaRepository;

public class DomainPersonaService implements PersonaService {
	private final PersonaRepository personaRepository;
	
	public DomainPersonaService (PersonaRepository personaRepository) {
		this.personaRepository = personaRepository;
	}

	@Override
	public Iterable<Persona> getPersonas(String id) throws Exception{
		return personaRepository.getPersonas(id);
	}

	@Override
	public Iterable<Persona> getListOrder(Iterable<Persona> listPersona, Integer order) {		
		return personaRepository.getListOrder(listPersona, order);
	}

	@Override
	public String savePersona(Persona persona) throws Exception {
		return this.personaRepository.savePersona(persona);
	}

	@Override
	public Persona getPersona(String dni) throws Exception {
		return this.personaRepository.getPersona(dni);
	}
	
}
