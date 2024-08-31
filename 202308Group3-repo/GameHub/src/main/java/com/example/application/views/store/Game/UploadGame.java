package com.example.application.views.store.Game;

import com.example.application.data.IndieGame;
import com.example.application.data.User;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;

import com.example.application.data.Upload18NConfig;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileBuffer;
import com.example.application.security.AuthenticatedUser;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Optional;
import java.util.stream.Stream;

@AnonymousAllowed
@Route("UploadFiles")
public class UploadGame extends FormLayout {
    private H3 pageTitle;
    private Span errorMessageField;
    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    public UploadGame(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;
        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();


            errorMessageField = new Span();
            pageTitle = new H3("Game Maker - Game Hub, Welcome " + user.getUsername() );

            // upload button
            // Upload button
            MultiFileBuffer buffer = new MultiFileBuffer();
            Upload upload = new Upload(buffer);
            upload.setAutoUpload(false);
            Upload18NConfig uploadConfig = new Upload18NConfig();
            uploadConfig.getAddFiles().setOne("Upload your game files here: ");
            upload.addSucceededListener(event -> {
                String fileName = event.getFileName();
                InputStream inputStream = buffer.getInputStream(fileName);
                saveFileToSystem(inputStream, fileName, user.getUsername());

                // Do something with the file data
                // processFile(inputStream, fileName);

            });
            add(pageTitle, upload);
        }
    }
    private void saveFileToSystem(InputStream inputStream, String fileName,String user) {
        try {
            int gameNumber = 1;


            // Specify the path where you want to save the file
            Path userDirectory = Paths.get("/Users/jose/Downloads/g-mehub 5/src/main/java/com/example/application/gameFiles", user);
            Files.createDirectories(userDirectory);
            String standardizedFileName = String.format("user%sgame%d.jpg",user,gameNumber);
            Path targetPath = userDirectory.resolve(standardizedFileName);
            Boolean nameExist = Files.exists(targetPath);

            while(nameExist){
                gameNumber++;
                standardizedFileName = String.format("user%sgame%d.jpg",user,gameNumber);
                targetPath = userDirectory.resolve(standardizedFileName);
                Files.exists(targetPath);
                if(Files.exists(targetPath) == false){
                    nameExist = false;
                }
            }

            // Use Files.copy to save the file to the filesystem
            Files.copy(inputStream,targetPath);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., show an error message to the user

            errorMessageField.setText("Error saving file to the system");
        }
    }

}