package com.qsystem.demo.infrastructure.adaptador.firebase;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Service
public class FirebaseInitialization {

	@PostConstruct
	public void initialization() {
		try {
			try {
				String firebaseConfig = System.getenv("FIREBASE_CREDENTIALS");
				if (firebaseConfig == null || firebaseConfig.isEmpty()) {
					throw new IllegalStateException("No se encontró la variable de entorno FIREBASE_CREDENTIALS");
				}

				ObjectMapper objectMapper = new ObjectMapper();
				byte[] firebaseJson = objectMapper.writeValueAsBytes(objectMapper.readTree(firebaseConfig));

				FirebaseOptions options = new FirebaseOptions.Builder()
						.setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(firebaseJson)))
						.build();

				FirebaseApp.initializeApp(options);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
