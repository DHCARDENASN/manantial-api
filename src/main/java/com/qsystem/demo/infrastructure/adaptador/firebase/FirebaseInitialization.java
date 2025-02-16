package com.qsystem.demo.infrastructure.adaptador.firebase;

import java.io.FileInputStream;
import java.io.IOException;

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
			if (FirebaseApp.getApps().isEmpty()) { // Verifica si ya está inicializado
				FileInputStream serviceAccount = new FileInputStream("./serviceAccountKey.json");
				FirebaseOptions options = new FirebaseOptions.Builder()
						.setCredentials(GoogleCredentials.fromStream(serviceAccount))
						.build();
				FirebaseApp.initializeApp(options);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
