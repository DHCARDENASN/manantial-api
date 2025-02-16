package com.qsystem.demo.aplication.service;

import com.google.cloud.firestore.DocumentReference;
import com.qsystem.demo.domain.model.*;
import com.qsystem.demo.domain.puerto.GestionRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class DomainCentroDistribucionService implements GestionService {

	
	private final GestionRepository gestionRepository;

	public DomainCentroDistribucionService(GestionRepository gestionRepository) {
		this.gestionRepository = gestionRepository;
	}

	public RegistrarAlumno registrarAlumno (RegistrarAlumno registrarAlumno) throws Exception{
		log.info("DomainCentroDistribucionService-saveCentroDistribucion");


		String idapoderado= gestionRepository.saveApoderado(registrarAlumno.getApoderado());

		String idcolegioprocedencia= gestionRepository.saveColegioProcedencia(registrarAlumno.getColegio_procedencia());

		log.info("idapoderado: "+idapoderado+", idcolegioprocedencia: "+idcolegioprocedencia);
		String idalumno= gestionRepository.saveAlumno(registrarAlumno.getAlumno(), registrarAlumno, idapoderado,  idcolegioprocedencia);

		log.info("idalumno: "+idalumno);
		return registrarAlumno;
	}

	@Override
	public Docente registrarDocente(Docente docente) throws Exception {
		log.info("DomainCentroDistribucionService-saveCentroDistribucion");
		String idalumno= gestionRepository.saveDocente(docente);
		return docente;
	}

	@Override
	public Area registrarArea(Area area) throws Exception {
		log.info("DomainCentroDistribucionService-registrarArea");
		String idarea= gestionRepository.saveArea(area);

		return area;
	}

	@Override
	public Competencia registrarCompetencia(Competencia competencia) throws Exception {
		log.info("DomainCentroDistribucionService-registrarArea");
		String idcompetencia= gestionRepository.saveCompetencia(competencia);
		return competencia;
	}

	@Override
	public Map<String, Object> registrarAreaCompetencia(AreaPorCompetencia areaPorCompetencia) throws Exception {
		log.info("DomainCentroDistribucionService-registrarArea");
		return gestionRepository.saveAreaCompetencia(areaPorCompetencia);
	}

	@Override
	public EnfoqueTransversal registrarEnfoqueTransversal(EnfoqueTransversal enfoqueTransversal) throws Exception {
		log.info("DomainCentroDistribucionService-registrarEnfoqueTransversal");
		String idenfoquetransversal= gestionRepository.saveEnfoqueTransversal(enfoqueTransversal);
		enfoqueTransversal.setIdenfoquetransversal(idenfoquetransversal);
		return enfoqueTransversal;
	}

	@Override
	public List<Map<String, Object>> listarAlumno(Map<String, Object> request) throws Exception {
		log.info("DomainCentroDistribucionService-listarAlumnos");
		return gestionRepository.listarAlumnos(request);
	}

	@Override
	public List<Map<String, Object>> listarDocente(Map<String, Object> request) throws Exception {
		log.info("DomainCentroDistribucionService-listarDocente");
		return gestionRepository.listarDocentes(request);
	}

	@Override
	public List<Map<String, Object>> listarArea(Map<String, Object> request) throws Exception {
		log.info("DomainCentroDistribucionService-listarArea");
		return gestionRepository.listarAreas(request);
	}

	@Override
	public List<Map<String, Object>> listarCompetencia(Map<String, Object> request) throws Exception {
		log.info("DomainCentroDistribucionService-listarCompetencia");
		return gestionRepository.listarCompetencias(request);
	}

	@Override
	public List<Map<String, Object>> listarAreaPorCompetencia(Map<String, Object> request) throws Exception {
		log.info("DomainCentroDistribucionService-listarAreaPorCompetencia");
		return gestionRepository.listarAreasPorCompetencias(request);
	}


	@Override
	public Iterable<ColegioProcedencia> getCentrosDistribucion() {
		return gestionRepository.getCentrosDistribucion();
	}

	@Override
	public ColegioProcedencia getDescripcion(String centroDistribucion) throws Exception {
		return gestionRepository.getDescripcion(centroDistribucion);
	}
	

}
