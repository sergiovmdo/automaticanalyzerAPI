package com.aaa.automaticanalyzer.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

@Component
@Slf4j
public class FirebaseMessagingConfiguration implements ApplicationRunner {
    private String firebaseProject = "automatic-analyzer";

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        log.debug("Initializing project {} from firebase", firebaseProject);

        FileInputStream serviceAccount =
                new FileInputStream("C:\\Users\\sergi\\OneDrive\\Escritorio\\TFG\\automatic-analyzer-firebase-adminsdk-sxh8o-7a9eda65b5.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
            log.debug("Project {} initialized", firebaseProject);
        } else {
            log.debug("Project {} was already initialized, skipping", firebaseProject);
        }
    }
}
