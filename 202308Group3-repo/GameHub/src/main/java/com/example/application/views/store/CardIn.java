package com.example.application.views.store;

import com.example.application.data.IndieGame;
import com.example.application.data.User;
import com.example.application.views.discounts.Deal;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Background;
import com.vaadin.flow.theme.lumo.LumoUtility.BorderRadius;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;

import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;

public class CardIn extends ListItem {



    public CardIn(Anchor anchor, String title, Double price1) {
        addClassNames(Background.CONTRAST_5, Display.FLEX, FlexDirection.COLUMN, AlignItems.START, Padding.MEDIUM,
                BorderRadius.LARGE, Width.FULL, Height.FULL);
        // Set the anchor's size to match the card size
        anchor.getStyle().set("width", "100%");
        anchor.getStyle().set("height", "100%");

        // Create a Div to contain the card content
        Div content = new Div();

//        Image image = thumbImage;
//        image.setWidth("100%");
//        image.setHeight("100%");
//
//        content.add(image);

        // Rest of your code to display title and prices
        Paragraph header = new Paragraph();
        header.addClassNames(FontSize.MEDIUM, FontWeight.SEMIBOLD);
        header.setWidth("190px");
        header.setHeight("95px");
        header.setText(title);

        HorizontalLayout row = new HorizontalLayout();

        Span badge1 = new Span();
        badge1.getElement().setAttribute("theme", "badge");
        badge1.setText(String.valueOf(price1));

        Span badge2 = new Span();
        badge2.getElement().setAttribute("theme", "badge");


            row.add(badge1);


        content.add(header, row);

        anchor.add(content); // Add the entire content to the anchor

        add(anchor);

        Button shopButton = new Button(VaadinIcon.CART.create());

//        shopButton.addClickListener(e -> {
//            VaadinSession vaadinSession = VaadinSession.getCurrent();
//            Notification notification;
//
//            ArrayList<Deal> shoppingCart = (ArrayList<Deal>) vaadinSession.getAttribute("shopGames");
//
////            if (shoppingCart == null) {
////                shoppingCart = new ArrayList<>();
////            }
////
////            if (!shoppingCart.contains(game)) {
////                shoppingCart.add(game);
////                vaadinSession.setAttribute("shopGames", shoppingCart);
////
////                // Update the session
////                notification = Notification.show("Added to Shopping Cart");
////            } else {
////                notification = Notification.show("Item is already in the Shopping Cart");
////            }
//
//            notification.setPosition(Notification.Position.MIDDLE);
//            notification.setDuration(3000);
//        });
        add(shopButton);


    }
}
