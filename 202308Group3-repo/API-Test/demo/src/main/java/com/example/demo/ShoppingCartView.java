package com.example.demo;

import com.example.demo.rest.Deal;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@Route("shopping")
@PageTitle("Shopping • Game Hub")
public class ShoppingCartView extends VerticalLayout {
    VerticalLayout rowsLayout = new VerticalLayout();

    public ShoppingCartView() {
        // Retrieve the shopping cart content (use your method of choice)
        VaadinSession vaadinSession = VaadinSession.getCurrent();
        ArrayList<Deal> shoppingCart = (ArrayList<Deal>) vaadinSession.getAttribute("shopGames");
        vaadinSession.setAttribute("shopGames", shoppingCart);
        if (shoppingCart == null) {
            shoppingCart = new ArrayList<>();
        }

        rowsLayout.removeAll();
        HorizontalLayout cardRow = new HorizontalLayout();
        int count = 0;
        int cardsPerRow = 5;
        for(int i = 0; i < shoppingCart.size(); i++) {
            Deal deal = shoppingCart.get(i);

            Image thumbImage = new Image(deal.getThumb(), "Thumbnail");
            thumbImage.setMaxWidth(190, Unit.PIXELS);
            thumbImage.setMaxHeight(95, Unit.PIXELS);
            Anchor anchor = new Anchor(deal.getLink());
            anchor.getStyle().set("display", "block");
            anchor.getStyle().set("width", "100%");
            Card item = new Card(anchor,thumbImage, deal.getTitle(), deal.getNormalPrice(), null);

            cardRow.add(item);
            count++;

            if (count % cardsPerRow == 0) {
                rowsLayout.add(cardRow);
                cardRow = new HorizontalLayout();
            }
        }

        rowsLayout.add(cardRow);
        rowsLayout.setAlignItems(Alignment.CENTER);

        add(rowsLayout);

        //shoppingCart.forEach(deal -> add(createCard(deal)));

        add(createTotalAmountLabel(shoppingCart));

        add(createBackToStoreButton());

        add(createBuyButton());
    }
    private Div createCard(Deal deal) {
        // Create a Div or other layout component to represent a card
        Div card = new Div();
        card.getStyle().set("border", "1px solid #ccc");
        card.getStyle().set("width", "300px");
        card.getStyle().set("height", "150px");
        card.getStyle().set("padding", "10px"); // Adjust the padding value as needed
    
        if (deal != null) {
            // Display deal information (customize based on your requirements)
            H2 title = new H2(deal.getTitle());
            // Add other components to display deal information (e.g., price, image)
    
            card.add(title);
        } else {
            // Handle the case when deal is null, e.g., log a message or display an error
            card.add(new H2("Deal information not available"));
        }
    
        return card;
    }
    private Paragraph createTotalAmountLabel(ArrayList<Deal> shoppingCart) {
        // Calculate the total amount based on the prices of games in the cart
        double totalAmount = shoppingCart.stream()
                .mapToDouble(deal -> Double.parseDouble(deal.getNormalPrice().replaceAll("[^0-9.]", "")))
                .sum();

        // Format the total amount as currency (customize based on your requirements)
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedTotalAmount = "$" + df.format(totalAmount);

        // Create a label to display the total amount
        Paragraph totalAmountLabel = new Paragraph("Total Amount: " + formattedTotalAmount);
        totalAmountLabel.getStyle().set("position", "fixed");
        totalAmountLabel.getStyle().set("bottom", "35px");
        totalAmountLabel.getStyle().set("right", "10px");

        return totalAmountLabel;
    }
     private Button createBuyButton() {
        // Create a Vaadin "Buy" button
        Button buyButton = new Button("Buy");
        buyButton.getStyle().set("position", "fixed");
        buyButton.getStyle().set("bottom", "10px");
        buyButton.getStyle().set("right", "10px");

        // Add click listener for the "Buy" button (customize based on your requirements)
        buyButton.addClickListener(e -> {
            // Add logic for the "Buy" action
            // For example, navigate to a checkout page or perform payment processing
        });

        return buyButton;
    }

    private Button createBackToStoreButton() {
        // Create a Vaadin "Back to Store" button
        Button backToStoreButton = new Button("Back to Store");
        backToStoreButton.getStyle().set("position", "fixed");
        backToStoreButton.getStyle().set("bottom", "10px");
        backToStoreButton.getStyle().set("left", "10px");

        // Add click listener for the "Back to Store" button (customize based on your requirements)
        backToStoreButton.addClickListener(e -> {
            // Add logic to navigate back to the store page
            getUI().ifPresent(ui -> ui.navigate("Store"));
        });

        return backToStoreButton;
    }
}


