package com.qsystem.demo.infrastructure.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qsystem.demo.aplication.service.GestionService;
import com.qsystem.demo.aplication.service.DomainCentroDistribucionService;
import com.qsystem.demo.aplication.service.DomainPersonaService;
import com.qsystem.demo.aplication.service.PersonaService;
import com.qsystem.demo.domain.puerto.GestionRepository;
import com.qsystem.demo.domain.puerto.PersonaRepository;


@Configuration
public class BeanConfiguration {

	@Bean
	GestionService centroDistribucionService(final GestionRepository gestionRepository) {
		return new DomainCentroDistribucionService(gestionRepository);
	}
	
	@Bean
	PersonaService personaService(final PersonaRepository personaRepository) {
		return new DomainPersonaService(personaRepository);
	}
}
