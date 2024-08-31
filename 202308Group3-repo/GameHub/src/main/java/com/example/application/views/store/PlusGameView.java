//package com.example.application.views.store;
//
//
//import com.example.application.services.GameService;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//import com.vaadin.flow.server.auth.AnonymousAllowed;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.notification.NotificationVariant;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.data.binder.BeanValidationBinder;
//import com.vaadin.flow.data.binder.ValidationException;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@AnonymousAllowed
//@Route("plus")
//@PageTitle("Plus • Game Hub")
//public class PlusGameView extends VerticalLayout {
//    @Autowired
//    private GameService gameService;
//
//
//    private void showSuccess(GameDetails userBean) {
//        Notification notification =
//                Notification.show("Data saved, " + userBean.getTitle() + " was added.");
//        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
//
//        // Here you'd typically redirect the user to another view
//    }
//
//    public PlusGameView() {
//        AddGameForm plusForm = new AddGameForm();
//        // Center the RegistrationForm
//        setHorizontalComponentAlignment(Alignment.CENTER, plusForm);
//
//        add(plusForm);
//        addBindingAndValidation(plusForm);
//    }
//
//    public void addBindingAndValidation(AddGameForm form) {
//        BeanValidationBinder<GameDetails> binder = new BeanValidationBinder<>(GameDetails.class);
//        binder.bindInstanceFields(form);
//
//        form.getSubmitButton().addClickListener(event -> {
//            try {
//                // Create empty bean to store the details into
//                GameDetails gameBean = new GameDetails();
//
//                // Run validators and write the values to the bean
//                binder.writeBean(gameBean);
//
//                GameDetails game = new GameDetails();
//                game.setAuthor(gameBean.getAuthor());
//                game.setTitle(gameBean.getTitle());
//                game.setPrice(gameBean.getPrice());
//                game.setDescription(gameBean.getDescription());
//
//
//                GameDetails savedGame = gameService.createGame(game);
//                showSuccess(gameBean);
//
//            } catch (ValidationException exception) {
//                // validation errors are already visible for each field,
//                // and bean-level errors are shown in the status label.
//                // We could show additional messages here if we want, do logging, etc.
//            }
//        });
//    }
//
//
//
//}