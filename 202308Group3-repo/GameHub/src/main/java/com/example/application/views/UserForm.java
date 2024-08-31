package com.example.application.views;

import com.example.application.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "user-form")
@AnonymousAllowed

public class UserForm extends VerticalLayout {

    private TextField username = new TextField("Username");
    private TextField name = new TextField("Name");
    private PasswordField password = new PasswordField("Password");
    private Button saveButton = new Button("Save", e -> save());

    @Autowired
    private UserService userService;

    public UserForm() {
        add(username, name, password, saveButton);
    }

    private void save() {
        // Get values from fields
        String usernameValue = username.getValue();
        String nameValue = name.getValue();
        String passwordValue = password.getValue();

        // Perform validation (you can add more complex validation here)
        if (usernameValue.isEmpty() || nameValue.isEmpty() || passwordValue.isEmpty()) {
            Notification.show("Please fill in all fields", 3000, Notification.Position.TOP_CENTER);
            return;
        }

        // Call the UserService to save the user

            Notification.show("User saved successfully", 3000, Notification.Position.TOP_CENTER);

            userService.createUser(usernameValue, nameValue, passwordValue);

        clearForm();

        // Show a success message

    }

    private void clearForm() {
        username.clear();
        name.clear();
        password.clear();
    }
}
