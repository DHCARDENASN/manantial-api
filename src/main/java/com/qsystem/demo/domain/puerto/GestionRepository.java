package com.qsystem.demo.domain.puerto;



import com.qsystem.demo.domain.model.*;

import java.util.List;
import java.util.Map;

/*
 Contrato/Interfaz que sirve para que la capa infrastructure 
 se conecte a la capa modelo
 */


public interface GestionRepository {
	
	String saveAlumno (Alumno alumno,RegistrarAlumno registrarAlumno, String idapoderado, String idcolegioprocedencia) throws Exception;
	String saveDocente (Docente docente) throws Exception;
	String saveArea (Area area) throws Exception;
	String saveCompetencia (Competencia competencia) throws Exception;
	String saveEnfoqueTransversal (EnfoqueTransversal enfoqueTransversal) throws Exception;
	Map<String, Object> saveAreaCompetencia(AreaPorCompetencia areaPorCompetencia) throws Exception;
	String saveColegioProcedencia (ColegioProcedencia colegioProcedencia) throws Exception;
	String saveApoderado (Apoderado apoderado) throws Exception;

	//list
	List<Map<String, Object>> listarAlumnos(Map<String, Object> request) throws Exception;
	List<Map<String, Object>> listarDocentes(Map<String, Object> request) throws Exception;
	List<Map<String, Object>> listarAreas(Map<String, Object> request) throws Exception;
	List<Map<String, Object>> listarCompetencias(Map<String, Object> request) throws Exception;
	List<Map<String, Object>> listarAreasPorCompetencias(Map<String, Object> request) throws Exception;

	Iterable<ColegioProcedencia> getCentrosDistribucion();
	ColegioProcedencia getDescripcion(String centroDistribucion) throws Exception;
	
}
