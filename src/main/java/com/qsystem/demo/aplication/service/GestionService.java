package com.qsystem.demo.aplication.service;

import com.qsystem.demo.domain.model.*;

import java.util.List;
import java.util.Map;

public interface GestionService {

	RegistrarAlumno registrarAlumno (RegistrarAlumno colegioProcedencia) throws Exception;
	Docente registrarDocente (Docente docente) throws Exception;
	Area registrarArea (Area area) throws Exception;
	Competencia registrarCompetencia(Competencia competencia) throws Exception;
	Map<String, Object> registrarAreaCompetencia(AreaPorCompetencia areaPorCompetencia) throws Exception;
	EnfoqueTransversal registrarEnfoqueTransversal(EnfoqueTransversal enfoqueTransversal) throws Exception;

	//listar
	List<Map<String, Object>> listarAlumno(Map<String, Object> request) throws Exception;
	List<Map<String, Object>> listarDocente(Map<String, Object> request) throws Exception;
	List<Map<String, Object>> listarArea(Map<String, Object> request) throws Exception;
	List<Map<String, Object>> listarCompetencia(Map<String, Object> request) throws Exception;
	List<Map<String, Object>> listarAreaPorCompetencia(Map<String, Object> request) throws Exception;

	Iterable<ColegioProcedencia> getCentrosDistribucion();
	ColegioProcedencia getDescripcion(String centroDistribucion) throws Exception;
}
