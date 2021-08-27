package web.api.escola.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FirebaseInitialization {
    @PostConstruct
    public void initialization(){
        try{
            FileInputStream serviceAccount = new FileInputStream("/home/daniel/Documents/fb_keys/projeto-web-escola-firebase-adminsdk-z71u9-9c48c36a35.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://projeto-web-escola-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
