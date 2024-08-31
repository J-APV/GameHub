package com.example.application.views.store.Game;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("pruchased")

public class PurchaseView {

    public PurchaseView() {
        // Create a label
        Label label = new Label("Hello, Vaadin!");

        // Create a button
        Button button = new Button("Click me");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Add a click listener to the button
        button.addClickListener(event -> label.setText("Button clicked!"));

        // Add the label and button to a layout
        VerticalLayout layout = new VerticalLayout( button);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        // Add the layout to the main VerticalLayout of the view

    }
}
