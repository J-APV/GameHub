package com.example.demo;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("Signup")
@PageTitle("Sign Up! • Game Hub")
public class SignUpView extends VerticalLayout {

    @Autowired
    private UserService userService;

    private boolean enablePasswordValidation;

    private void showSuccess(UserDetails userBean) {
        Notification notification =
                Notification.show("Data saved, welcome " + userBean.getFirstName());
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        // Here you'd typically redirect the user to another view
    }


    public SignUpView() {
        SignUpForm registrationForm = new SignUpForm();
        // Center the RegistrationForm
        setHorizontalComponentAlignment(Alignment.CENTER, registrationForm);

        add(registrationForm);
        addBindingAndValidation(registrationForm);
    }

    public void addBindingAndValidation(SignUpForm registrationForm) {
        BeanValidationBinder<UserDetails> binder = new BeanValidationBinder<>(UserDetails.class);
        binder.bindInstanceFields(registrationForm);

        // A custom validator for password fields
//        binder.forField(registrationForm.getPasswordField())
//                .withValidator(this::passwordValidator).bind("password");
//
//        // The second password field is not connected to the Binder, but we
//        // want the binder to re-check the password validator when the field
//        // value changes. The easiest way is just to do that manually.
//        registrationForm.getPasswordConfirmField().addValueChangeListener(e -> {
//            // The user has modified the second field, now we can validate and show errors.
//            // See passwordValidator() for how this flag is used.
//            enablePasswordValidation = true;
//
//            binder.validate();
//        });

        // Set the label where bean-level error messages go
//        binder.setStatusLabel(registrationForm.getErrorMessageField());

        // And finally the submit button
        registrationForm.getSubmitButton().addClickListener(event -> {
            try {
                // Create empty bean to store the details into
                UserDetails userBean = new UserDetails();

                // Run validators and write the values to the bean
                binder.writeBean(userBean);

                // Typically, you would here call backend to store the bean
                UserDetails user = new UserDetails();
                user.setUserName(userBean.getUserName());
                user.setFirstName(userBean.getFirstName());
                user.setLastName(userBean.getLastName());
                user.setPassword(userBean.getPassword());
                user.setEmail(userBean.getEmail());

                UserDetails savedUser = userService.createUser(user);
                // Show success message if everything went well
                showSuccess(userBean);
            } catch (ValidationException exception) {
                // validation errors are already visible for each field,
                // and bean-level errors are shown in the status label.
                // We could show additional messages here if we want, do logging, etc.
            }
        });
    }
}