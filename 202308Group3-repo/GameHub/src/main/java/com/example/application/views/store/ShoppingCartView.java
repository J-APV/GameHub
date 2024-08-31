package com.example.application.views.store;


import com.example.application.Card;
import com.example.application.views.discounts.Deal;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;

@PermitAll
@Route("shopping")
@PageTitle("Shopping • Game Hub")
public class ShoppingCartView extends VerticalLayout {
    VerticalLayout rowsLayout = new VerticalLayout();
    Button buyButton = new Button();
    ArrayList<Deal> shoppingCart;
    public ShoppingCartView() {
        // Retrieve the shopping cart content (use your method of choice)
        VaadinSession vaadinSession = VaadinSession.getCurrent();
         shoppingCart = (ArrayList<Deal>) vaadinSession.getAttribute("shopGames");
        vaadinSession.setAttribute("shopGames", shoppingCart);
        if (shoppingCart == null) {
            shoppingCart = new ArrayList<>();
        }

        buyButton.addClickListener(e -> UI.getCurrent().navigate(PurchaseView.class));
    buyButton.setText("Buy");
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

        add(buyButton,rowsLayout);
    }
    public  ArrayList<Deal> getCart(){
        return shoppingCart;
    }

}
