package com.example.application;
import com.example.application.data.Bookmark;
import com.example.application.data.UserRepository;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;

public class Card extends ListItem {
    private UserRepository userRepository;
    private Deal deal;
    public Card(Anchor anchor, Image thumbImage, String title, String salePrice, String regularPrice, Deal deal, UserRepository userRepository) {
        addClassNames(Background.CONTRAST_5, Display.FLEX, FlexDirection.COLUMN, AlignItems.START, Padding.MEDIUM,
                BorderRadius.LARGE, Width.FULL, Height.FULL);

        this.userRepository = userRepository;
        this.deal = deal;
        // Set the anchor's size to match the card size
        anchor.getStyle().set("width", "100%");
        anchor.getStyle().set("height", "100%");

        // Create a Div to contain the card content
        Div content = new Div();

        Image image = thumbImage;
        image.setWidth("100%");
        image.setHeight("100%");

        content.add(image);

        // Rest of your code to display title and prices
        Paragraph header = new Paragraph();
        header.addClassNames(FontSize.MEDIUM, FontWeight.SEMIBOLD);
        header.setWidth("190px");
        header.setHeight("95px");
        header.setText(title);

        HorizontalLayout row = new HorizontalLayout();

        Span badge1 = new Span();
        badge1.getElement().setAttribute("theme", "badge");
        badge1.setText(salePrice);

        Span badge2 = new Span();
        badge2.getElement().setAttribute("theme", "badge");
        badge2.setText(regularPrice);

        row.add(badge1, badge2);

        content.add(header, row);

        anchor.add(content); // Add the entire content to the anchor

        add(anchor);

        Button bookmarkButton = new Button(VaadinIcon.BOOKMARK.create());
        bookmarkButton.addClickListener(event -> bookmarkDeal(userRepository, deal));

        		getUI().ifPresent(ui -> ui.navigate("login"));



        add(bookmarkButton);


    }

    public Card(Anchor anchor, Image thumbImage, String title, String price1, String price2, Deal deal) {
        addClassNames(Background.CONTRAST_5, Display.FLEX, FlexDirection.COLUMN, AlignItems.START, Padding.MEDIUM,
                BorderRadius.LARGE, Width.FULL, Height.FULL);
        // Set the anchor's size to match the card size
        anchor.getStyle().set("width", "100%");
        anchor.getStyle().set("height", "100%");

        // Create a Div to contain the card content
        Div content = new Div();

        Image image = thumbImage;
        image.setWidth("100%");
        image.setHeight("100%");

        content.add(image);

        // Rest of your code to display title and prices
        Paragraph header = new Paragraph();
        header.addClassNames(FontSize.MEDIUM, FontWeight.SEMIBOLD);
        header.setWidth("190px");
        header.setHeight("95px");
        header.setText(title);

        HorizontalLayout row = new HorizontalLayout();

        Span badge1 = new Span();
        badge1.getElement().setAttribute("theme", "badge");
        badge1.setText(price1);

        Span badge2 = new Span();
        badge2.getElement().setAttribute("theme", "badge");
        badge2.setText(price2);

        if(price2 == null) {
            row.add(badge1);
        } else {
            row.add(badge1, badge2);
        }

        content.add(header, row);

        anchor.add(content); // Add the entire content to the anchor

        add(anchor);

        Button shopButton = new Button(VaadinIcon.CART.create());

        shopButton.addClickListener(e -> {
            VaadinSession vaadinSession = VaadinSession.getCurrent();
            Notification notification;

            ArrayList<Deal> shoppingCart = (ArrayList<Deal>) vaadinSession.getAttribute("shopGames");

            if (shoppingCart == null) {
                shoppingCart = new ArrayList<>();
            }

            if (!shoppingCart.contains(deal)) {
                shoppingCart.add(deal);
                vaadinSession.setAttribute("shopGames", shoppingCart);

                // Update the session
                notification = Notification.show("Added to Shopping Cart");
            } else {
                notification = Notification.show("Item is already in the Shopping Cart");
            }

            notification.setPosition(Notification.Position.MIDDLE);
            notification.setDuration(3000);
        });
        add(shopButton);


    }



    private void bookmarkDeal(UserRepository userRepository, Deal deal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String loggedInUsername = authentication.getName();
            User loggedInUser = userRepository.findByUsername(loggedInUsername);
            if (loggedInUser != null) {
                Bookmark bookmark = new Bookmark(deal);
                loggedInUser.addBookmark(bookmark);
                userRepository.save(loggedInUser);
                Notification.show("Deal bookmarked!");
            } else {
                Notification.show("User not found!");
            }
        } else {
            Notification.show("Please log in to bookmark deals.");
        }
    }

    public Card(Anchor anchor, Image thumbImage, String title, String salePrice, String regularPrice) {
        addClassNames(Background.CONTRAST_5, Display.FLEX, FlexDirection.COLUMN, AlignItems.START, Padding.MEDIUM,
                BorderRadius.LARGE, Width.FULL, Height.FULL);


        anchor.getStyle().set("width", "100%");
        anchor.getStyle().set("height", "100%");

        // Create a Div to contain the card content
        Div content = new Div();

        Image image = thumbImage;
        image.setWidth("100%");
        image.setHeight("100%");

        content.add(image);

        // Rest of your code to display title and prices
        Paragraph header = new Paragraph();
        header.addClassNames(FontSize.MEDIUM, FontWeight.SEMIBOLD);
        header.setWidth("190px");
        header.setHeight("95px");
        header.setText(title);

        HorizontalLayout row = new HorizontalLayout();

        Span badge1 = new Span();
        badge1.getElement().setAttribute("theme", "badge");
        badge1.setText(salePrice);

        Span badge2 = new Span();
        badge2.getElement().setAttribute("theme", "badge");
        badge2.setText(regularPrice);

        row.add(badge1, badge2);

        content.add(header, row);

        anchor.add(content); // Add the entire content to the anchor

        add(anchor);





    }


}
