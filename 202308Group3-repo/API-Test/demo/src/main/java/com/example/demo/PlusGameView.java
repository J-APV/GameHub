package com.example.demo;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin.End;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Route("plus")
@PageTitle("Plus • Game Hub")
public class PlusGameView extends VerticalLayout {

    private List<String> availableGenres = new ArrayList<>();
    private List<String> selectedGenres = new ArrayList<>();
    private Select<String> genreSelect;
    private Div selectedGenresDiv;
    private Image coverImage;
    private ByteArrayOutputStream uploadedImageBuffer;

    public PlusGameView() {
        TextField titleField = new TextField();
        TextField authorField = new TextField();
        NumberField priceField = new NumberField();
        TextArea descriptionField = new TextArea();
        genreSelect = new Select<>();
        selectedGenresDiv = new Div();
        coverImage = new Image();
        coverImage.setMaxHeight("300px");
        coverImage.setVisible(false);

        titleField.setLabel("Title");
        titleField.setPlaceholder("Game...");
        authorField.setLabel("Author Name");
        authorField.setPlaceholder("Name...");
        descriptionField.setLabel("Description");
        descriptionField.setPlaceholder("Description...");
        titleField.setWidth("300px");
        authorField.setWidth("300px");
        descriptionField.setWidth("615px");
        descriptionField.setMinHeight("200px");
        priceField.setLabel("Price");
        priceField.setPlaceholder("$");
        availableGenres.add("Action");
        availableGenres.add("Adventure");
        availableGenres.add("RPG");
        availableGenres.add("Strategy");
        availableGenres.add("Simulation");
        availableGenres.add("Sports");
        availableGenres.add("Puzzle");
        availableGenres.add("Horror");

        genreSelect.setItems(availableGenres);
        genreSelect.setPlaceholder("Select genre(s)");
        //genreSelect.setLabel("Genre");
        genreSelect.setWidth("200px");

        Button addGenreButton = new Button("Add Genre");
        addGenreButton.addClickListener(e -> {
            String selectedGenre = genreSelect.getValue();
            if (selectedGenre != null) {
                Div selectedGenreDiv = new Div();
                selectedGenreDiv.setText(selectedGenre);
                selectedGenreDiv.addClickListener(event -> {
                    selectedGenresDiv.remove(selectedGenreDiv);
                    selectedGenres.remove(selectedGenre);
                    availableGenres.add(selectedGenre);
                    genreSelect.setItems(availableGenres);
                });
                selectedGenresDiv.add(selectedGenreDiv);
                selectedGenres.add(selectedGenre);
                genreSelect.setValue(null);
                availableGenres.remove(selectedGenre);
                genreSelect.setItems(availableGenres);
            }
        });

        HorizontalLayout genreLayout = new HorizontalLayout(genreSelect, addGenreButton);

        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("image/*");
        upload.setMaxFiles(1);
        upload.setMaxFileSize(1024 * 1024); // 1MB

        upload.addSucceededListener(event -> {
            InputStream inputStream = buffer.getInputStream();
            uploadedImageBuffer = new ByteArrayOutputStream();
            try {
                int read;
                byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    uploadedImageBuffer.write(bytes, 0, read);
                }
                StreamResource resource = new StreamResource("coverImage", () -> new ByteArrayInputStream(uploadedImageBuffer.toByteArray()));
                coverImage.setSrc(resource);
                coverImage.setVisible(true);
            } catch (Exception e) {
                Notification.show("Error processing uploaded image.", 3000, Position.MIDDLE);
            }
        });

        upload.addFailedListener(event -> {
            Notification.show("File upload failed. Please check the file and try again.", 3000, Position.MIDDLE);
        });
        VerticalLayout uploadLayout = new VerticalLayout(upload, coverImage);
        uploadLayout.setAlignSelf(Alignment.END);
        HorizontalLayout titleAuthorLayout = new HorizontalLayout(titleField, authorField, priceField, uploadLayout);
        titleAuthorLayout.setSpacing(true);
        VerticalLayout addGame = new VerticalLayout();
        Button addGameButton = new Button("Add Game");
        addGameButton.addClickListener( e -> UI.getCurrent().navigate("Store"));
        addGame.setAlignSelf(Alignment.END, addGameButton);
        addGame.add(addGameButton);
        add(titleAuthorLayout, descriptionField, genreLayout, selectedGenresDiv, addGame);
        
        setupLayout();
    }

    private void setupLayout() {
        // You can set up your layout here if needed
    }
}
