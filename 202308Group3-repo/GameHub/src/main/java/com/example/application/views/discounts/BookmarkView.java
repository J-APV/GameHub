package com.example.application.views.discounts;

import com.example.application.Card;
import com.example.application.data.Bookmark;
import com.example.application.views.MainLayout;
import com.example.application.views.discounts.Deal;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;


import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@PermitAll
@Route(value = "bookmarks", layout = MainLayout.class)
@PageTitle("Shopping • Game Hub")
public class BookmarkView extends VerticalLayout {
    VerticalLayout rowsLayout = new VerticalLayout();

    private final BookmarkService bookmarkService;

    public BookmarkView(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
        displayBookmarks();
    }

    private void displayBookmarks() {
        addClassNames(LumoUtility.Background.CONTRAST_5, LumoUtility.Display.FLEX, LumoUtility.FlexDirection.COLUMN, LumoUtility.AlignItems.START, LumoUtility.Padding.MEDIUM,
                LumoUtility.BorderRadius.LARGE, LumoUtility.Width.FULL, LumoUtility.Height.FULL);
        List<Bookmark> userBookmarks = bookmarkService.getCurrentUserBookmarks();

        HorizontalLayout cardRow = new HorizontalLayout();
        int count = 0;
        int cardsPerRow = 5;

        for (Bookmark bookmark : userBookmarks) {
            Image thumbImage = new Image(bookmark.getThumb(), "Thumbnail");
            String salePrice = "$" + bookmark.getSalePrice();
            String regularPrice = "$" + bookmark.getNormalPrice();
            Anchor anchor = new Anchor(bookmark.getUrl());
            anchor.getStyle().set("display", "block");
            anchor.getStyle().set("width", "100%");

            Button deleteButton = new Button("Delete", event -> {
                Button clickedButton = (Button) event.getSource();
                HorizontalLayout parentLayout = null;
                if (clickedButton.getParent().isPresent()) {
                    Optional<Component> optionalParent = clickedButton.getParent();
                    Component parentComponent = optionalParent.get();
                    if (parentComponent instanceof HorizontalLayout) {
                        parentLayout = (HorizontalLayout) parentComponent;
                    }
                }

                if (parentLayout != null) {
                    // Now we have the parent layout; remove the card
                    boolean removed = bookmarkService.removeBookmark(bookmark);
                    if (removed) {
                        parentLayout.removeAll(); // Remove both card and delete button
                        rowsLayout.remove(parentLayout); // Remove the row if it becomes empty
                    }
                }
                UI.getCurrent().navigate("my-view");
            });

            Card item = new Card(anchor, thumbImage, bookmark.getTitle(), salePrice, regularPrice);

            cardRow.add(item, deleteButton);
            cardRow.getStyle().set("margin", "40px 0  0");
            count++;

            if (count % cardsPerRow == 0) {
                rowsLayout.add(cardRow);
                cardRow = new HorizontalLayout();
            }
        }

        rowsLayout.add(cardRow);
        rowsLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        add(rowsLayout);




    }
}
