package com.qsystem.demo.infrastructure.rest.controller;

import com.qsystem.demo.domain.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.qsystem.demo.aplication.service.GestionService;
import com.qsystem.demo.aplication.service.PersonaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class RestApiController {
	private static Logger log = LoggerFactory.getLogger(RestApiController.class);
	
	private final GestionService gestionService;
	private final PersonaService personaService;
	
	public RestApiController(GestionService gestionService, PersonaService personaService) {
		this.gestionService = gestionService;
		this.personaService = personaService;
	}
	
	
	@PutMapping("/primerejercicio/agregar")
	public ResponseEntity savePersona(@RequestBody Persona persona) throws Exception{
		log.info("PUT - Ingresando a savePersona..");
		this.personaService.savePersona(persona);
		return new ResponseEntity<>(this.personaService.getPersona(persona.getDni()), HttpStatus.CREATED);
	}
	
	
	@GetMapping("/primerejercicio/{order}")
	public ResponseEntity<Iterable<Persona>> listPersonas(@PathVariable String order) throws Exception{
		log.info("Ingresando a listPersonas..");
		return new ResponseEntity<>(this.personaService.getPersonas(order), HttpStatus.OK);
	}
	
	
	@GetMapping("/segundoejercicio")
	public ResponseEntity<Iterable<ColegioProcedencia>> listCentroDistribucion(){
		log.info("Ingresando a listCentroDistribucion..");
		return new ResponseEntity<>(this.gestionService.getCentrosDistribucion(), HttpStatus.OK);
	}
			
	@PutMapping("/registrar-alumno")
	public ResponseEntity saveAlumno(@RequestBody RegistrarAlumno registrarAlumno) throws Exception{
		log.info("PUT - Ingresando a saveAlumno..");
		RegistrarAlumno alumnoRegistrado = gestionService.registrarAlumno(registrarAlumno);

		return new ResponseEntity<>(alumnoRegistrado, HttpStatus.CREATED);
	}

	@PutMapping("/registrar-docente")
	public ResponseEntity saveDocente(@RequestBody Docente docente) throws Exception{
		log.info("PUT - Ingresando a saveDocente..");
		Docente docenteRegistrado = gestionService.registrarDocente(docente);

		return new ResponseEntity<>(docenteRegistrado, HttpStatus.CREATED);
	}

	@PutMapping("/registrar-area")
	public ResponseEntity saveArea(@RequestBody Area area) throws Exception{
		log.info("PUT - Ingresando a saveDocente..");
		Area areaRegistrada = gestionService.registrarArea(area);

		return new ResponseEntity<>(areaRegistrada, HttpStatus.CREATED);
	}

	@PutMapping("/registrar-competencia")
	public ResponseEntity saveCompetencia(@RequestBody Competencia competencia) throws Exception{
		log.info("PUT - Ingresando a saveCompetencia..");
		Competencia competenciaRegistrada = gestionService.registrarCompetencia(competencia);

		return new ResponseEntity<>(competenciaRegistrada, HttpStatus.CREATED);
	}

	@PutMapping("/registrar-area-competencia")
	public ResponseEntity saveAreaCompetencia(@RequestBody AreaPorCompetencia areaPorCompetencia) throws Exception{
		log.info("PUT - Ingresando a saveCompetencia..");
		Map<String, Object> areaPorCompetenciaRegistrada = gestionService.registrarAreaCompetencia(areaPorCompetencia);

		return new ResponseEntity<>(areaPorCompetenciaRegistrada, HttpStatus.CREATED);
	}

	@PutMapping("/registrar-enfoque-transversal")
	public ResponseEntity saveEnfoqueTransversal(@RequestBody EnfoqueTransversal enfoqueTransversal) throws Exception{
		log.info("PUT - Ingresando a saveCompetencia..");
		EnfoqueTransversal enfoqueTransversalRegistrado = gestionService.registrarEnfoqueTransversal(enfoqueTransversal);

		return new ResponseEntity<>(enfoqueTransversalRegistrado, HttpStatus.CREATED);
	}

	@PostMapping("/listar-alumno")
	public ResponseEntity listarAlumnos(@RequestBody(required = false) Map<String, Object> request) throws Exception{
		log.info("POST - Ingresando a listarAlumnos..");
		if(request == null){
			request = new HashMap<>();
		}
		List<Map<String, Object>> listAlumnos = gestionService.listarAlumno(request);

		return new ResponseEntity<>(listAlumnos, HttpStatus.OK);
	}

	@PostMapping("/listar-docente")
	public ResponseEntity listarDocente(@RequestBody(required = false) Map<String, Object> request) throws Exception{
		log.info("POST - Ingresando a listarDocente..");
		List<Map<String, Object>> listDocente = gestionService.listarDocente(request);

		return new ResponseEntity<>(listDocente, HttpStatus.OK);
	}

	@PostMapping("/listar-area")
	public ResponseEntity listarArea(@RequestBody(required = false) Map<String, Object> request) throws Exception{
		log.info("POST - Ingresando a listarArea..");
		List<Map<String, Object>> listArea = gestionService.listarArea(request);

		return new ResponseEntity<>(listArea, HttpStatus.OK);
	}

	@PostMapping("/listar-competencia")
	public ResponseEntity listarCompetencia(@RequestBody(required = false) Map<String, Object> request) throws Exception{
		log.info("POST - Ingresando a listarArea..");
		List<Map<String, Object>> listarCompetencia= gestionService.listarCompetencia(request);

		return new ResponseEntity<>(listarCompetencia, HttpStatus.OK);
	}

	@PostMapping("/listar-area-competencia")
	public ResponseEntity listarAreaPorCompetencia(@RequestBody(required = false) Map<String, Object> request) throws Exception{
		log.info("POST - Ingresando a listarArea..");
		List<Map<String, Object>> listarAreaCompetencia= gestionService.listarAreaPorCompetencia(request);

		return new ResponseEntity<>(listarAreaCompetencia, HttpStatus.OK);
	}

	
		
}
