package com.example.application.views.store;// PurchaseView.java

import com.example.application.views.discounts.Deal;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import javax.annotation.security.PermitAll;

@AnonymousAllowed
@Route("purchase")
public class PurchaseView extends VerticalLayout {
    H1 header = new H1();

    private TextField cardNumber = new TextField();
    private TextField expiryMonth = new TextField();
    private TextField expiryYear = new TextField();
    private TextField cvc = new TextField();

    public PurchaseView() {


        setAlignItems(Alignment.CENTER);
        setSpacing(true);
        setPadding(true);
        getStyle().set("padding-left", "10%");
        getStyle().set("padding-right", "10%");
        setWidthFull();

        // This VerticalLayout contains addressHeader, formLayout, saveButton
        VerticalLayout billingAddressLayout = new VerticalLayout();
        billingAddressLayout.setWidth("65%");
        billingAddressLayout.setMinWidth("300px");
        billingAddressLayout.setSpacing(true);
        billingAddressLayout.setPadding(false);

        // Flex-grow of the formLayout and Grid are set to 1 to be grown with
        // the same portion.
        billingAddressLayout.getElement().getStyle().set("flex-grow", "1");

        // Main header of the form

        header.setText("Checkout form");
        header.setWidthFull();
        header.getStyle().set("text-align", "center");
        header.getStyle().set("font-weight", "bold");

        // To give the page the ability of wrapping, a HorizontalLayout is used,
        // it contains a formLayout and a grid. flex-wrap and flex-direction
        // help to wrap the page and give the direction of wrapping.
        // When it is wrapped Grid goes up.
        HorizontalLayout contentLayout = new HorizontalLayout();
        contentLayout.addClassName("contentlayout");
        contentLayout.setSpacing(false);
        contentLayout.setHeightFull();
        contentLayout.setWidthFull();

        // A formLayout is used to add the fields. It is also responsive.
        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("10em", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("20em", 3,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("30em", 6,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));

        formLayout.setWidthFull();

        // Header for the Billing address fields.


        H2 paymentHeader = new H2();
        paymentHeader.setText("Payment");
        formLayout.setColspan(formLayout.addFormItem(paymentHeader, ""), 6);

        // Items related to payment are created and added to the FormLayout.
        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.setItems("Credit card", "Debit card", "PayPal");
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        formLayout.setColspan(formLayout.addFormItem(radioGroup, ""), 6);



        cardNumber.setWidthFull();
        formLayout.setColspan(
                formLayout.addFormItem(cardNumber, "Credit card number"),
                3);


        expiryMonth.setWidthFull();
        expiryYear.setWidthFull();
        formLayout.setColspan(formLayout.addFormItem(expiryMonth, "Expiration Month"),
                1);
        formLayout.setColspan(formLayout.addFormItem(expiryYear, "Expiration year"),
                1);


        cvc.setWidthFull();
        formLayout.setColspan(formLayout.addFormItem(cvc, "CVV"), 1);

        Button saveButton = new Button("Continue to checkout",
                new Icon(VaadinIcon.ARROW_RIGHT));
        saveButton.addThemeVariants(ButtonVariant.LUMO_LARGE,
                ButtonVariant.LUMO_PRIMARY);

        // Aligns button to the right
        saveButton.getStyle().set("margin-left", "auto");

        saveButton.addClickListener(e->initiatePayment());


        // Using a grid to show the purchased items.
        // Using a grid to show the purchased items.
        VerticalLayout itemsLayout = new VerticalLayout();
        itemsLayout.setWidth("30%");

        Deal deal = new Deal("First Upload", "https://wallpapers.com/images/featured/among-us-cb21ldue3llceiua.jpg", "$10.00");

// Iterate through the list and create Labels for each item

        H5 nameLabel = new H5(deal.getTitle());
        Label descriptionLabel = new Label(deal.getThumb());
        H6 priceLabel = new H6(deal.getNormalPrice());

        // Wrap labels in a Div or another layout component
        Div itemContainer = new Div(nameLabel, priceLabel, new Hr());

        // Add the container to the itemsLayout
        itemsLayout.add(itemContainer);



        billingAddressLayout.add( formLayout, saveButton);
        contentLayout.add( itemsLayout,billingAddressLayout);
        add(header, contentLayout);



    }

    private void initiatePayment() {

        // Replace "your_stripe_secret_key" with your actual Stripe test secret key
        Stripe.apiKey = "sk_test_51OKcDGJqUTWxqEsite32OmpCkHPjrk4UT4KM5nFBjnTxImpsybmeiKS2NmMSyPpWUXL9AoCA1K3L7T41HPHHOVHC002BuOEiQK";


        try {
            // Create a PaymentIntent on the server
            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setAmount(1000L) // Amount in cents, adjust accordingly (e.g., $10)
                    .setCurrency("usd")
                    .build();

            PaymentIntent intent = PaymentIntent.create(createParams);

            // Handle the client-side confirmation and complete the payment
            String clientSecret = intent.getClientSecret();



            UI.getCurrent().getPage().executeJs("confirmPayment($0, $1)", clientSecret, "pk_test_51OKcDGJqUTWxqEsihXkcII4M1eKrKdemFx6tbbtxPX2aZuytd7JP82ZX15mpnIFBODLakVpqfFJ7YZ8aWF9cGxHr00XU0rgQXT");

            PaymentIntent resource = PaymentIntent.retrieve(intent.getId());
            PaymentIntentConfirmParams params =
                    PaymentIntentConfirmParams.builder()
                            .setPaymentMethod("pm_card_visa")
                            .setReturnUrl("https://www.example.com")
                            .build();
            PaymentIntent paymentIntent = resource.confirm(params);

            Notification.show(paymentIntent.getStatus());





if(paymentIntent.getStatus()=="succeeded"){
    Notification.show("Payment attempt successful");

}
        } catch (StripeException e) {
            Notification.show("Error: " + e.getMessage());
        }
    }

}
