package com.example.application.data;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileBuffer;
import com.vaadin.flow.component.upload.receivers.FileData;

public class UploadFileBuffer extends Div {
    public UploadFileBuffer(){

        /* Example for MultiFileBuffer */
        MultiFileBuffer multiFileBuffer = new MultiFileBuffer();
        Upload multiFileUpload = new Upload(multiFileBuffer);
        multiFileUpload.addSucceededListener(event -> {
            // Determine which file was uploaded successfully
            String uploadFileName = event.getFileName();
            // Get information for that specific file
            FileData savedFileData = multiFileBuffer
                    .getFileData(uploadFileName);
            String absolutePath = savedFileData.getFile().getAbsolutePath();

            System.out.printf("File saved to: %s%n", absolutePath);
        });
    }
}