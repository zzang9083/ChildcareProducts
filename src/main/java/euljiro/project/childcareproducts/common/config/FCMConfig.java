package euljiro.project.childcareproducts.common.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FCMConfig {

    @Value("${fcm.file_path}") // your firebase sdk path
    private String firebaseSdkPath;


    @Value("${fcm.project_id}") // application.yml에 추가 필요
    private String firebaseProjectId;



    @Bean
    FirebaseApp firebaseApp() throws IOException {

        ClassPathResource firebaseResource = new ClassPathResource(firebaseSdkPath);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(
                        firebaseResource.getInputStream()))
                .setProjectId(firebaseProjectId)
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        return FirebaseMessaging.getInstance(firebaseApp());
    }
}
