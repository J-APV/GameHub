//package com.example.application.views.store.Game;
//
//import com.example.application.views.discounts.Deal;
//import com.example.application.views.store.ShoppingCartView;
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.PaymentIntent;
//import com.stripe.param.PaymentIntentCreateParams;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.button.ButtonVariant;
//import com.vaadin.flow.component.charts.model.Label;
//import com.vaadin.flow.component.checkbox.Checkbox;
//import com.vaadin.flow.component.dependency.CssImport;
//import com.vaadin.flow.component.formlayout.FormLayout;
//import com.vaadin.flow.component.html.*;
//import com.vaadin.flow.component.icon.Icon;
//import com.vaadin.flow.component.icon.VaadinIcon;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
//import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
//import com.vaadin.flow.component.select.Select;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//import jakarta.annotation.security.PermitAll;
//
//import java.text.NumberFormat;
//import java.util.List;
//import java.util.Locale;
//@PermitAll
//@Route("checkout-form")
//@PageTitle("Checkout form")
//
//public class CheckoutFormView extends VerticalLayout {
//    private ShoppingCartView items;
//    H1 header = new H1();
//
//    TextField cardNumber = new TextField();
//    TextField nameOnCard = new TextField();
//    private TextField expiryMonth = new TextField("Expiry Month");
//    private TextField expiryYear = new TextField("Expiry Year");
//    TextField cvc = new TextField();
//    public CheckoutFormView() {
//
//        setAlignItems(Alignment.CENTER);
//        setSpacing(true);
//        setPadding(true);
//        getStyle().set("padding-left", "10%");
//        getStyle().set("padding-right", "10%");
//        setWidthFull();
//
//        // This VerticalLayout contains addressHeader, formLayout, saveButton
//        VerticalLayout billingAddressLayout = new VerticalLayout();
//        billingAddressLayout.setWidth("65%");
//        billingAddressLayout.setMinWidth("300px");
//        billingAddressLayout.setSpacing(true);
//        billingAddressLayout.setPadding(false);
//
//        // Flex-grow of the formLayout and Grid are set to 1 to be grown with
//        // the same portion.
//        billingAddressLayout.getElement().getStyle().set("flex-grow", "1");
//
//        // Main header of the form
//
//        header.setText("Checkout form");
//        header.setWidthFull();
//        header.getStyle().set("text-align", "center");
//        header.getStyle().set("font-weight", "bold");
//
//        // To give the page the ability of wrapping, a HorizontalLayout is used,
//        // it contains a formLayout and a grid. flex-wrap and flex-direction
//        // help to wrap the page and give the direction of wrapping.
//        // When it is wrapped Grid goes up.
//        HorizontalLayout contentLayout = new HorizontalLayout();
//        contentLayout.addClassName("contentlayout");
//        contentLayout.setSpacing(false);
//        contentLayout.setHeightFull();
//        contentLayout.setWidthFull();
//
//        // A formLayout is used to add the fields. It is also responsive.
//        FormLayout formLayout = new FormLayout();
//        formLayout.setResponsiveSteps(
//                new FormLayout.ResponsiveStep("10em", 1,
//                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
//                new FormLayout.ResponsiveStep("20em", 3,
//                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
//                new FormLayout.ResponsiveStep("30em", 6,
//                        FormLayout.ResponsiveStep.LabelsPosition.TOP));
//
//        formLayout.setWidthFull();
//
//        // Header for the Billing address fields.
//
//
//        H2 paymentHeader = new H2();
//        paymentHeader.setText("Payment");
//        formLayout.setColspan(formLayout.addFormItem(paymentHeader, ""), 6);
//
//        // Items related to payment are created and added to the FormLayout.
//        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
//        radioGroup.setItems("Credit card", "Debit card", "PayPal");
//        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
//        formLayout.setColspan(formLayout.addFormItem(radioGroup, ""), 6);
//
//        nameOnCard.setWidthFull();
//        formLayout.setColspan(
//                formLayout.addFormItem(nameOnCard, "Name on card"), 3);
//
//        cardNumber.setWidthFull();
//        formLayout.setColspan(
//                formLayout.addFormItem(cardNumber, "Credit card number"),
//                3);
//
//
//        expiryMonth.setWidthFull();
//        expiryYear.setWidthFull();
//        formLayout.setColspan(formLayout.addFormItem(expiryMonth, "Expiration Month"),
//                1);
//        formLayout.setColspan(formLayout.addFormItem(expiryYear, "Expiration year"),
//                1);
//
//
//        cvc.setWidthFull();
//        formLayout.setColspan(formLayout.addFormItem(cvc, "CVV"), 1);
//
//        Button saveButton = new Button("Continue to checkout",
//                new Icon(VaadinIcon.ARROW_RIGHT));
//        saveButton.addThemeVariants(ButtonVariant.LUMO_LARGE,
//                ButtonVariant.LUMO_PRIMARY);
//
//        // Aligns button to the right
//        saveButton.getStyle().set("margin-left", "auto");
//
//
//
//
//        // Using a grid to show the purchased items.
//        // Using a grid to show the purchased items.
//        VerticalLayout itemsLayout = new VerticalLayout();
//        itemsLayout.setWidth("30%");
//
//        Deal deal = new Deal("First Upload", "https://wallpapers.com/images/featured/among-us-cb21ldue3llceiua.jpg", "$10.00");
//
//// Iterate through the list and create Labels for each item
//
//            H5 nameLabel = new H5(deal.getTitle());
//            Label descriptionLabel = new Label(deal.getThumb());
//            H6 priceLabel = new H6(deal.getNormalPrice());
//
//            // Wrap labels in a Div or another layout component
//            Div itemContainer = new Div(nameLabel, priceLabel, new Hr());
//
//            // Add the container to the itemsLayout
//            itemsLayout.add(itemContainer);
//
//
//
//        billingAddressLayout.add( formLayout, saveButton);
//        contentLayout.add( itemsLayout,billingAddressLayout);
//        add(header, contentLayout);
//
//
//
//
//    }
//    public PurchaseView() {
//        Button purchaseButton = new Button("Confirm Purchase", e -> initiatePayment());
//        add(cardNumber.getValue(), expiryMonth.getValue(), expiryYear, cVV, purchaseButton);
//    }
//
//    private void initiatePayment() {
//        // Replace "your_stripe_secret_key" with your actual Stripe test secret key
//        Stripe.apiKey = "sk_test_51OKcDGJqUTWxqEsite32OmpCkHPjrk4UT4KM5nFBjnTxImpsybmeiKS2NmMSyPpWUXL9AoCA1K3L7T41HPHHOVHC002BuOEiQK";
//
//        try {
//            // Create a PaymentIntent on the server
//            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
//                    .setAmount(1000L) // Amount in cents, adjust accordingly (e.g., $10)
//                    .setCurrency("usd")
//                    .build();
//
//            PaymentIntent intent = PaymentIntent.create(createParams);
//
//            // Handle the client-side confirmation and complete the payment
//            String clientSecret = intent.getClientSecret();
//            Notification.show("Client Secret: " + clientSecret);
//
//            // Redirect to a confirmation page or trigger additional UI updates
//            // You may want to use Vaadin's UI.navigateTo method to navigate to a confirmation page
//        } catch (StripeException e) {
//            Notification.show("Error: " + e.getMessage());
//        }
//    }
//
//}