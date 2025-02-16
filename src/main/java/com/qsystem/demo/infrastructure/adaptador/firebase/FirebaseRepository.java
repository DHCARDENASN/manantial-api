package com.qsystem.demo.infrastructure.adaptador.firebase;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import com.qsystem.demo.domain.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.qsystem.demo.Constantes;
import com.qsystem.demo.domain.puerto.GestionRepository;
import com.qsystem.demo.domain.puerto.PersonaRepository;
import com.qsystem.demo.infrastructure.exceptions.ResourceNotFoundException;

@Repository
public class FirebaseRepository  implements GestionRepository, PersonaRepository{
	private static Logger log = LoggerFactory.getLogger(FirebaseRepository.class);
	private static final String FORMAT_DATE ="dd-MM-yyyy HH:mm:ss";
	private static final String COLLECTION_NAME_CENTRO_DISTRIBUCION = "centro-distribucion";
	private static final String COLLECTION_NAME_PERSONAS = "personas";
	private static final String COLLECTION_ALUMNO = "alumno";
	private static final String COLLECTION_COLEGIO_PROCEDENCIA = "colegio-procedencia";
	private static final String COLLECTION_APODERADO = "apoderado";
	private static final String COLLECTION_DOCENTE= "docente";
	private static final String COLLECTION_AREA= "area";
	private static final String COLLECTION_COMPETENCIA= "competencia";
	private static final String COLLECTION_AREA_COMPETENCIA= "area-competencia";
	private static final String COLLECTION_ENFOQUE_TRANSVERSAL= "enfoque-transversal";

	public String saveAlumno(Alumno alumno, RegistrarAlumno registrarAlumno, String apoderadoId, String colegioId) throws Exception {
		log.info("FirebaseRepository-saveAlumno");
		Date date = new Date();
		Firestore dbFireStore = FirestoreClient.getFirestore();

		// Crear referencia al apoderado
		DocumentReference apoderadoRef = dbFireStore.collection(COLLECTION_APODERADO).document(apoderadoId);

		// Crear referencia al colegio de procedencia
		DocumentReference colegioRef = dbFireStore.collection(COLLECTION_COLEGIO_PROCEDENCIA).document(colegioId);

		// Asignar las referencias al alumno
		alumno.setApoderado(apoderadoRef); // Aquí deberías tener un campo de tipo DocumentReference en tu clase Alumno
		alumno.setColegioProcedencia(colegioRef); // Similar con colegioProcedencia
		alumno.setFecha_creacion(date);  // Fecha actual
		alumno.setUsuario_creacion(registrarAlumno.getUsuario_creacion());   // Usuario actual (puede ser el nombre de usuario o id del usuario logueado)
		alumno.setFecha_modificacion(date);  // Fecha actual
		alumno.setUsuario_modificacion(registrarAlumno.getUsuario_creacion());   // Usuario actual

		ApiFuture<DocumentReference> collectionApiFuture = dbFireStore.collection(COLLECTION_ALUMNO).add(alumno);

		//actualizando con valores de fecha
		registrarAlumno.setFecha_creacion(date);
		registrarAlumno.setFecha_modificacion(date);
		registrarAlumno.setUsuario_modificacion(registrarAlumno.getUsuario_creacion());
		return collectionApiFuture.get().getId();  // Retorna el ID del alumno guardado
	}

	@Override
	public String saveDocente(Docente docente) throws Exception {
		log.info("FirebaseRepository-saveDocente");
		Date date = new Date();

		//actualizando con valores de auditoria
		docente.setFecha_creacion(date);
		docente.setFecha_modificacion(date);
		docente.setUsuario_modificacion(docente.getUsuario_creacion());

		Firestore dbFireStore = FirestoreClient.getFirestore();
		ApiFuture<DocumentReference> collectionApiFuture=  dbFireStore.collection(COLLECTION_DOCENTE)
				.add(docente);
		return collectionApiFuture.get().getId();
	}

	@Override
	public String saveArea(Area area) throws Exception {
		log.info("FirebaseRepository-saveArea");
		Date date = new Date();

		//actualizando con valores de auditoria
		area.setFecha_creacion(date);
		area.setFecha_modificacion(date);
		area.setUsuario_modificacion(area.getUsuario_creacion());

		Firestore dbFireStore = FirestoreClient.getFirestore();
		ApiFuture<DocumentReference> collectionApiFuture=  dbFireStore.collection(COLLECTION_AREA)
				.add(area);
		return collectionApiFuture.get().getId();
	}

	@Override
	public String saveCompetencia(Competencia competencia) throws Exception {
		log.info("FirebaseRepository-saveCompetencia");
		Date date = new Date();

		//actualizando con valores de auditoria
		competencia.setFecha_creacion(date);
		competencia.setFecha_modificacion(date);
		competencia.setUsuario_modificacion(competencia.getUsuario_creacion());

		Firestore dbFireStore = FirestoreClient.getFirestore();
		ApiFuture<DocumentReference> collectionApiFuture=  dbFireStore.collection(COLLECTION_COMPETENCIA)
				.add(competencia);
		return collectionApiFuture.get().getId();
	}

	@Override
	public String saveEnfoqueTransversal(EnfoqueTransversal enfoqueTransversal) throws Exception {
		log.info("FirebaseRepository-saveEnfoqueTransversal");

		Firestore dbFireStore = FirestoreClient.getFirestore();
		ApiFuture<DocumentReference> collectionApiFuture=  dbFireStore.collection(COLLECTION_ENFOQUE_TRANSVERSAL)
				.add(enfoqueTransversal);
		return collectionApiFuture.get().getId();
	}

	@Override
	public Map<String, Object> saveAreaCompetencia(AreaPorCompetencia areaPorCompetencia) throws Exception {
		log.info("FirebaseRepository-saveAreaCompetencia");
		Date date = new Date();

		//actualizando con valores de auditoria
		areaPorCompetencia.setFecha_creacion(date);
		areaPorCompetencia.setFecha_modificacion(date);
		areaPorCompetencia.setUsuario_modificacion(areaPorCompetencia.getUsuario_creacion());

		Firestore dbFireStore = FirestoreClient.getFirestore();

		DocumentReference areaRef =
				dbFireStore.collection(COLLECTION_AREA).document(areaPorCompetencia.getArea());

		DocumentReference competenciaRef =
				dbFireStore.collection(COLLECTION_COMPETENCIA).document(areaPorCompetencia.getCompetencia());

		Map<String, Object> areaCompetenciaData = new HashMap<>();
		areaCompetenciaData.put("area", areaRef);
		areaCompetenciaData.put("competencia", competenciaRef);
		areaCompetenciaData.put("fecha_creacion", date);
		areaCompetenciaData.put("usuario_creacion", areaPorCompetencia.getUsuario_creacion());
		areaCompetenciaData.put("fecha_modificacion", date);
		areaCompetenciaData.put("usuario_modificacion", areaPorCompetencia.getUsuario_creacion());

		ApiFuture<DocumentReference> collectionApiFuture = dbFireStore.collection(COLLECTION_AREA_COMPETENCIA)
				.add(areaCompetenciaData);

		// Recuperar los datos completos del área y la competencia
		Map<String, Object> response = new HashMap<>();
		response.put("idareaporcompetencia", collectionApiFuture.get().getId());

		// Obtener los datos del área
		DocumentSnapshot areaSnapshot = areaRef.get().get();
		if (areaSnapshot.exists()) {
			response.put(COLLECTION_AREA, areaSnapshot.getData()); // Todos los datos del área
		}

		// Obtener los datos de la competencia
		DocumentSnapshot competenciaSnapshot = competenciaRef.get().get();
		if (competenciaSnapshot.exists()) {
			response.put(COLLECTION_COMPETENCIA, competenciaSnapshot.getData()); // Todos los datos de la competencia
		}
		return response;
	}


	@Override
	public String saveColegioProcedencia(ColegioProcedencia colegioProcedencia) throws Exception {
		log.info("FirebaseRepository-saveColegioProcedencia");

		Firestore dbFireStore = FirestoreClient.getFirestore();
		ApiFuture<DocumentReference> collectionApiFuture=  dbFireStore.collection(COLLECTION_COLEGIO_PROCEDENCIA)
				.add(colegioProcedencia);

		return collectionApiFuture.get().getId();
	}

	@Override
	public String saveApoderado(Apoderado apoderado) throws Exception {
		log.info("FirebaseRepository-saveApoderado");

		Firestore dbFireStore = FirestoreClient.getFirestore();
		ApiFuture<DocumentReference> collectionApiFuture=  dbFireStore.collection(COLLECTION_APODERADO)
				.add(apoderado);

		return collectionApiFuture.get().getId();
	}

	@Override
	public List<Map<String, Object>> listarAlumnos(Map<String, Object> request) throws Exception {
		Firestore dbFireStore = FirestoreClient.getFirestore();

		String grado = (String) request.get("grado");

		// Consultar todos los alumnos si no se envió el grado o filtrar por el grado si se proporcionó
		ApiFuture<QuerySnapshot> querySnapshotApiFuture;
		if (grado != null && !grado.isEmpty()) {
			querySnapshotApiFuture = dbFireStore.collection(COLLECTION_ALUMNO)
					.whereEqualTo("grado", grado)  // Filtrar por el campo "grado"
					.get();
		} else {
			// Si no se envió el grado, obtener todos los alumnos
			querySnapshotApiFuture = dbFireStore.collection(COLLECTION_ALUMNO).get();
		}

		// Obtener los documentos consultados
		QuerySnapshot querySnapshot = querySnapshotApiFuture.get();

		// Crear una lista para almacenar los alumnos
		List<Map<String, Object>> alumnosList = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		// Iterar sobre los documentos de la colección 'alumnos'
		for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
			Map<String, Object> alumnoMap = new HashMap<>();
			alumnoMap.put("idalumno", documentSnapshot.getId());  // ID del documento
			alumnoMap.put("nombres", documentSnapshot.getString("nombres"));
			alumnoMap.put("apellidos", documentSnapshot.getString("apellidos"));
			alumnoMap.put("tipo_documento", documentSnapshot.getLong("tipo_documento"));
			alumnoMap.put("numero_documento", documentSnapshot.getString("numero_documento"));
			alumnoMap.put("codigo_estudiante", documentSnapshot.getString("codigo_estudiante"));
			alumnoMap.put("grado", documentSnapshot.getString("grado"));
			alumnoMap.put("direccion", documentSnapshot.getString("direccion"));
			alumnoMap.put("usuario_creacion", documentSnapshot.getString("usuario_creacion"));
			alumnoMap.put("usuario_modificacion",  documentSnapshot.getString("usuario_modificacion"));
			Date fechaCreacion = documentSnapshot.getDate("fecha_creacion");
			if (fechaCreacion != null) {
				alumnoMap.put("fecha_creacion", dateFormat.format(fechaCreacion));  // Formato de la fecha
			}else{
				alumnoMap.put("fecha_creacion", null);  // Formato de la fecha
			}
			Date fechaModificacion = documentSnapshot.getDate("fecha_modificacion");
			if (fechaModificacion != null) {
				alumnoMap.put("fecha_modificacion", dateFormat.format(fechaModificacion));  // Formato de la fecha
			}else{
				alumnoMap.put("fecha_modificacion", null);  // Formato de la fecha
			}
			alumnosList.add(alumnoMap);
		}

		return alumnosList;
	}

	@Override
	public List<Map<String, Object>> listarDocentes(Map<String, Object> request) throws Exception {
		Firestore dbFireStore = FirestoreClient.getFirestore();

		ApiFuture<QuerySnapshot> querySnapshotApiFuture = dbFireStore.collection(COLLECTION_DOCENTE).get();

		QuerySnapshot querySnapshot = querySnapshotApiFuture.get();

		List<Map<String, Object>> docentesList = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
			Map<String, Object> docenteMap = new HashMap<>();
			docenteMap.put("iddocente", documentSnapshot.getId());  // ID del documento
			docenteMap.put("nombres", documentSnapshot.getString("nombres"));
			docenteMap.put("apellidos", documentSnapshot.getString("apellidos"));
			docenteMap.put("tipo_documento", documentSnapshot.getLong("tipo_documento"));
			docenteMap.put("numero_documento", documentSnapshot.getString("numero_documento"));
			docenteMap.put("grado", documentSnapshot.getString("grado"));
			docenteMap.put("seccion", documentSnapshot.getString("seccion"));

			docenteMap.put("usuario_creacion", documentSnapshot.getString("usuario_creacion"));
			docenteMap.put("usuario_modificacion",  documentSnapshot.getString("usuario_modificacion"));
			Date fechaCreacion = documentSnapshot.getDate("fecha_creacion");
			if (fechaCreacion != null) {
				docenteMap.put("fecha_creacion", dateFormat.format(fechaCreacion));  // Formato de la fecha
			}else{
				docenteMap.put("fecha_creacion", null);  // Formato de la fecha
			}
			Date fechaModificacion = documentSnapshot.getDate("fecha_modificacion");
			if (fechaModificacion != null) {
				docenteMap.put("fecha_modificacion", dateFormat.format(fechaModificacion));  // Formato de la fecha
			}else{
				docenteMap.put("fecha_modificacion", null);
			}
			docentesList.add(docenteMap);
		}
		return docentesList;
	}

	@Override
	public List<Map<String, Object>> listarAreas(Map<String, Object> request) throws Exception {

		Firestore dbFireStore = FirestoreClient.getFirestore();

		ApiFuture<QuerySnapshot> querySnapshotApiFuture = dbFireStore.collection(COLLECTION_AREA).get();

		QuerySnapshot querySnapshot = querySnapshotApiFuture.get();

		List<Map<String, Object>> areasList = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
			Map<String, Object> areaMap = new HashMap<>();
			areaMap.put("idarea", documentSnapshot.getId());  // ID del documento
			areaMap.put("nombre", documentSnapshot.getString("nombre"));
			areaMap.put("descripcion", documentSnapshot.getString("descripcion"));
			areaMap.put("tiene_libro", documentSnapshot.getBoolean("tiene_libro"));

			areaMap.put("usuario_creacion", documentSnapshot.getString("usuario_creacion"));
			areaMap.put("usuario_modificacion",  documentSnapshot.getString("usuario_modificacion"));
			Date fechaCreacion = documentSnapshot.getDate("fecha_creacion");
			if (fechaCreacion != null) {
				areaMap.put("fecha_creacion", dateFormat.format(fechaCreacion));  // Formato de la fecha
			}else{
				areaMap.put("fecha_creacion", null);  // Formato de la fecha
			}
			Date fechaModificacion = documentSnapshot.getDate("fecha_modificacion");
			if (fechaModificacion != null) {
				areaMap.put("fecha_modificacion", dateFormat.format(fechaModificacion));  // Formato de la fecha
			}else{
				areaMap.put("fecha_modificacion", null);
			}
			areasList.add(areaMap);
		}
		return areasList;
	}

	@Override
	public List<Map<String, Object>> listarCompetencias(Map<String, Object> request) throws Exception {
		Firestore dbFireStore = FirestoreClient.getFirestore();

		ApiFuture<QuerySnapshot> querySnapshotApiFuture = dbFireStore.collection(COLLECTION_COMPETENCIA).get();

		QuerySnapshot querySnapshot = querySnapshotApiFuture.get();

		List<Map<String, Object>> areasList = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
			Map<String, Object> areaMap = new HashMap<>();
			areaMap.put("idcompetencia", documentSnapshot.getId());  // ID del documento
			areaMap.put("nombre", documentSnapshot.getString("nombre"));
			areaMap.put("descripcion", documentSnapshot.getString("descripcion"));
			areaMap.put("transversal", documentSnapshot.getBoolean("transversal"));

			areaMap.put("usuario_creacion", documentSnapshot.getString("usuario_creacion"));
			areaMap.put("usuario_modificacion",  documentSnapshot.getString("usuario_modificacion"));
			Date fechaCreacion = documentSnapshot.getDate("fecha_creacion");
			if (fechaCreacion != null) {
				areaMap.put("fecha_creacion", dateFormat.format(fechaCreacion));  // Formato de la fecha
			}else{
				areaMap.put("fecha_creacion", null);  // Formato de la fecha
			}
			Date fechaModificacion = documentSnapshot.getDate("fecha_modificacion");
			if (fechaModificacion != null) {
				areaMap.put("fecha_modificacion", dateFormat.format(fechaModificacion));  // Formato de la fecha
			}else{
				areaMap.put("fecha_modificacion", null);
			}
			areasList.add(areaMap);
		}
		return areasList;
	}

	@Override
	public List<Map<String, Object>> listarAreasPorCompetencias(Map<String, Object> request) throws Exception {
		log.info("FirebaseRepository-listarAreaCompetencia");

		Firestore dbFireStore = FirestoreClient.getFirestore();

		String areaId = (String) request.get("area"); // Recuperar el filtro del área

		ApiFuture<QuerySnapshot> querySnapshotApiFuture;

		// Si se proporciona un 'area', filtrar por esa área, si no, obtener todos
		if (areaId != null && !areaId.isEmpty()) {
			// Crear una referencia del documento "area" usando el ID proporcionado
			DocumentReference areaRef = dbFireStore.collection(COLLECTION_AREA).document(areaId);

			// Filtrar por la referencia de área
			querySnapshotApiFuture = dbFireStore.collection(COLLECTION_AREA_COMPETENCIA)
					.whereEqualTo(COLLECTION_AREA, areaRef)
					.get();
		} else {
			// Si no se proporciona área, obtener todos los registros
			querySnapshotApiFuture = dbFireStore.collection(COLLECTION_AREA_COMPETENCIA).get();
		}

		// Obtener los documentos consultados
		QuerySnapshot querySnapshot = querySnapshotApiFuture.get();

		List<Map<String, Object>> areaCompetenciaList = new ArrayList<>();

		// Iterar sobre los documentos obtenidos
		for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
			Map<String, Object> areaCompetenciaMap = new HashMap<>();

			// Agregar los datos del área y competencia
			areaCompetenciaMap.put("idareaporcompetencia", documentSnapshot.getId());  // ID de la relación área-competencia

			// Obtener los datos del área
			DocumentReference areaRef = (DocumentReference) documentSnapshot.get(COLLECTION_AREA);
			DocumentSnapshot areaSnapshot = areaRef.get().get();
			if (areaSnapshot.exists()) {
				areaCompetenciaMap.put(COLLECTION_AREA, areaSnapshot.getData());
			}

			// Obtener los datos de la competencia
			DocumentReference competenciaRef = (DocumentReference) documentSnapshot.get(COLLECTION_COMPETENCIA);
			DocumentSnapshot competenciaSnapshot = competenciaRef.get().get();
			if (competenciaSnapshot.exists()) {
				areaCompetenciaMap.put(COLLECTION_COMPETENCIA, competenciaSnapshot.getData());
			}

			// Agregar los datos de la relación a la lista
			areaCompetenciaList.add(areaCompetenciaMap);
		}

		return areaCompetenciaList;
	}

	@Override
	public Iterable<ColegioProcedencia> getCentrosDistribucion()  {
		log.info("FirebaseRepository-getCentrosDistribucion");
		List<ColegioProcedencia> listCentros = new ArrayList();
		try {
			Firestore dbFireStore = FirestoreClient.getFirestore();
			ApiFuture<QuerySnapshot> future=  dbFireStore.collection(COLLECTION_NAME_CENTRO_DISTRIBUCION)
														.get();
			List<QueryDocumentSnapshot> documents = future.get().getDocuments();
			for (QueryDocumentSnapshot document : documents) {			  
			  listCentros.add(document.toObject(ColegioProcedencia.class));
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return listCentros;
	}

	@Override
	public ColegioProcedencia getDescripcion(String nombreCentroDistribucion) throws Exception{
		log.info("FirebaseRepository-getDescripcion");
		ColegioProcedencia colegioProcedencia = null;
		
		Firestore dbFireStore = FirestoreClient.getFirestore();
		
		DocumentReference documentReference=  dbFireStore.collection(COLLECTION_NAME_CENTRO_DISTRIBUCION)
													.document(nombreCentroDistribucion.toUpperCase());
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		
		DocumentSnapshot document = future.get();
		
		
		if(document.exists()) {
			colegioProcedencia = document.toObject(ColegioProcedencia.class);
		}else {
			throw new ResourceNotFoundException("Recurso no encontrado");
		}
		
		return colegioProcedencia;
		
	}
	
	@Override
	public String savePersona(Persona persona) throws Exception {
		log.info("FirebaseRepository-savePersona");
		
		Firestore dbFireStore = FirestoreClient.getFirestore();
		
		ApiFuture<WriteResult> collectionApiFuture=  dbFireStore.collection(COLLECTION_NAME_PERSONAS)
													.document(persona.getDni())
													.set(persona);
						
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	@Override
	public Iterable<Persona> getPersonas(String order) throws Exception {
		log.info("FirebaseRepository-getCentrosDistribucion");
		List<Persona> listPersonas = new ArrayList();
		List<Persona> personasOrdenadas = new ArrayList();	
		
		Firestore dbFireStore = FirestoreClient.getFirestore();
			
			ApiFuture<QuerySnapshot> future=  dbFireStore.collection(COLLECTION_NAME_PERSONAS)
														.get();
										 
			List<QueryDocumentSnapshot> documents = future.get().getDocuments();
			for (QueryDocumentSnapshot document : documents) {			  
				listPersonas.add(document.toObject(Persona.class));
				
			}
			
			switch (order){
			 case Constantes.ORDER_BY_RANDOM:
	                System.out.println("Ordenando Random: ");
	                personasOrdenadas= listPersonas;
	                Collections.shuffle(listPersonas);
	                break;
            case Constantes.ORDER_BY_DNI:
                System.out.println("Ordenando por DNI: ");
                 personasOrdenadas = listPersonas.stream()
                        .sorted((p1, p2) -> p1.getDni().compareTo(p2.getDni()))
                        .collect(Collectors.toList());
                break;
            case Constantes.ORDER_BY_APPATERNO:
                System.out.println("Ordenando por apellido paterno: ");
                personasOrdenadas = listPersonas.stream()
                        .sorted((p1, p2) -> p1.getAppPaterno().compareTo(p2.getAppPaterno()))
                        .collect(Collectors.toList());
                break;
            default:
            	throw new ResourceNotFoundException("Parametro no encontrado");               
        }
								
		
		return personasOrdenadas;
	}

	@Override
	public Iterable<Persona> getListOrder(Iterable<Persona> listPersona, Integer order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona getPersona(String dni) throws Exception {
		log.info("FirebaseRepository-getDescripcion");
		Persona persona = null;
		
		Firestore dbFireStore = FirestoreClient.getFirestore();
		
		DocumentReference documentReference=  dbFireStore.collection(COLLECTION_NAME_PERSONAS)
													.document(dni);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		
		DocumentSnapshot document = future.get();
		
		
		if(document.exists()) {
			persona = document.toObject(Persona.class);
		}else {
			throw new ResourceNotFoundException("Recurso no encontrado");
		}
		
		
													
		return persona;
	}

	

}
