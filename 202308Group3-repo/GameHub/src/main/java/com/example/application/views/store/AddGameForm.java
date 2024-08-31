package com.example.application.views.store;


import com.example.application.data.IndieGame;
import com.example.application.services.GameService;
import com.example.application.services.UserService;
import com.example.application.views.store.Game.UploadGame;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.Global;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import oshi.jna.platform.mac.SystemB;

import java.util.stream.Stream;

@AnonymousAllowed
@Route("addGame")
public class AddGameForm extends FormLayout {


    @Autowired
    private GameService gameService;
    private H3 pageTitle;

    private TextField title;
    private TextField author;
    private NumberField price;
    private TextArea description;
    private TextField thumbnail;
    private Span errorMessageField;
    private TextField uploadFile;
    private Button submitButton;




    public AddGameForm() {

        pageTitle = new H3("Game Maker - Game Hub");
        title = new TextField("Game Title");
        author = new TextField("Author");
        price = new NumberField("Price");
        description = new TextArea("Description");



        Div dollarPrefix = new Div();
        dollarPrefix.setText("$");
        price.setPrefixComponent(dollarPrefix);


        setRequiredIndicatorVisible(title, author, price, description);

        errorMessageField = new Span();

        // Upload button



        // Add a button with string submit
        submitButton = new Button("Add Game", e -> saveAndNextPage());
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(pageTitle, title, author, price, description, errorMessageField, submitButton);
        // Max width of the Form
        setMaxWidth("500px");

        // Allow the form layout to be responsive.
        // On device widths 0-490px we have one column.
        // Otherwise, we have two columns.
        setResponsiveSteps(
                new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));

        // These components always take full width
        setColspan(pageTitle, 8);
        setColspan(title, 2);
        setColspan(author, 2);
        setColspan(price, 2);
        setColspan(description, 2);
        setColspan(errorMessageField, 2);
        setColspan(submitButton, 8);

    }

    public Span getErrorMessageField() { return errorMessageField; }

    public Button getSubmitButton() { return submitButton; }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }

    private void save() {
        String titleVal = title.getValue();
        String authorVal = author.getValue();
        Double priceVal = price.getValue();
        String descVal = description.getValue();


        // Get file path from the field

        if (titleVal.isEmpty() || authorVal.isEmpty() || priceVal == null || descVal.isEmpty()) {
            Notification.show("Please fill in all fields and provide a file path", 3000, Notification.Position.TOP_CENTER);
            return;
        }




        gameService.createGame(titleVal,descVal,authorVal,priceVal);

        clearForm();

        // Show a success message

    }
    private void saveAndNextPage(){
        save();
        UI.getCurrent().navigate(UploadGame.class);
    }
    private void clearForm() {
        title.clear();
        author.clear();
        price.clear();
        description.clear();
        uploadFile.clear();
    }



}