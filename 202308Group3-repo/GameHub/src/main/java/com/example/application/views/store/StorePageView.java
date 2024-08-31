package com.example.application.views.store;

import com.example.application.Card;
import com.example.application.views.MainLayout;

import com.example.application.views.discounts.Deal;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@PermitAll
@Route(value = "Store", layout = MainLayout.class)
@PageTitle("Indie Game Store • Game Hub")
@Uses(Icon.class)
public class StorePageView extends Composite<VerticalLayout> {

    static int idSeed = 0;
    private List<Deal> displayedDeals;
    private ArrayList<Deal> shoppingCart;
    private final ComboBox<String> forumsDrop = new ComboBox<>("Options");
    private final VerticalLayout rowsLayout = new VerticalLayout();
    private final ComboBox<String> sortComboBox = new ComboBox<>("Sort");

    public StorePageView() {
        if(idSeed == 0){
            VaadinSession vaadinSession = VaadinSession.getCurrent();
            vaadinSession.setAttribute("session", vaadinSession);
            vaadinSession.setAttribute("shopGames", shoppingCart);
            idSeed++;
        }


        HorizontalLayout layoutRow = new HorizontalLayout();

        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow3 = new HorizontalLayout();



        Button buttonPrimary3 = new Button();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        TextField searchBar = new TextField();
        HorizontalLayout layoutRow4 = new HorizontalLayout();

        HorizontalLayout layoutRow5 = new HorizontalLayout();
        Button buttonPrimary4 = new Button();
        Button buttonPrimary5 = new Button();

        // add game button
        Button addButton = new Button();
        addButton.setText("Add Game");
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("addGame")));

        HorizontalLayout layoutRow6 = new HorizontalLayout();
        Button buttonPrimary6 = new Button();
        Button buttonPrimary7 = new Button();
        HorizontalLayout layoutRow7 = new HorizontalLayout();
        getContent().setHeightFull();
        getContent().setWidthFull();
        layoutRow.setWidthFull();
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);

        layoutRow.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow2.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutRow2.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        layoutColumn2.setHeightFull();
        layoutRow2.setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth(null);
        layoutRow3.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow3.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutRow3.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        buttonPrimary3.setText("Cart");
        layoutRow3.setAlignSelf(FlexComponent.Alignment.START, buttonPrimary3);
        buttonPrimary3.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary3.addClickListener(e -> UI.getCurrent().navigate(ShoppingCartView.class));

        layoutColumn3.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn3);
        layoutColumn4.setSpacing(false);
        layoutColumn4.addClassName(LumoUtility.Padding.XLARGE);
        layoutColumn4.setWidthFull();
        layoutColumn4.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutColumn4.setAlignItems(FlexComponent.Alignment.CENTER);
        searchBar.setValueChangeMode(ValueChangeMode.EAGER);
        searchBar.setLabel("Search by Title");
        searchBar.setWidthFull();
        layoutColumn4.setAlignSelf(FlexComponent.Alignment.CENTER, searchBar);
        layoutRow4.setWidthFull();
        layoutRow4.addClassName(LumoUtility.Gap.MEDIUM);



        layoutRow4.setAlignSelf(FlexComponent.Alignment.START, sortComboBox);

        setupSortSelect();

        layoutRow4.setFlexGrow(1.0, layoutRow5);
        layoutRow5.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow5.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutRow5.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonPrimary4.setText("Previous page");
        layoutRow5.setAlignSelf(FlexComponent.Alignment.END, buttonPrimary4);
        buttonPrimary4.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary5.setText("Next Page");
        layoutRow5.setAlignSelf(FlexComponent.Alignment.END, buttonPrimary5);
        buttonPrimary5.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        layoutRow6.setWidthFull();
        layoutRow6.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow6.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutRow6.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonPrimary6.setText("Previous page");
        layoutRow6.setAlignSelf(FlexComponent.Alignment.END, buttonPrimary6);
        buttonPrimary6.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary7.setText("Next Page");
        layoutRow6.setAlignSelf(FlexComponent.Alignment.END, buttonPrimary7);
        buttonPrimary7.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutRow7.setWidthFull();
        layoutRow7.addClassName(LumoUtility.Gap.MEDIUM);
        getContent().add(layoutRow);
        layoutRow.add(layoutRow2);
        layoutRow2.add(layoutColumn2);
        layoutColumn2.add(layoutRow3);
        layoutRow3.add(addButton, buttonPrimary3);
        getContent().add(layoutColumn3);
        layoutColumn3.add(layoutColumn4);
        layoutColumn4.add(searchBar);
        searchBar.addValueChangeListener(e -> updateList(e.getValue()));
        layoutColumn3.add(layoutRow4);
        layoutRow4.add(sortComboBox);
        layoutRow4.add(layoutRow5);
        layoutRow5.add(buttonPrimary4);
        layoutRow5.add(buttonPrimary5);
        layoutColumn3.add(rowsLayout);
        templateCards();
        layoutColumn3.add(layoutRow6);
        layoutRow6.add(buttonPrimary6);
        layoutRow6.add(buttonPrimary7);
        getContent().add(layoutRow7);

        VaadinSession one = VaadinSession.getCurrent();
        one.setAttribute("List" , displayedDeals );
    }


    private void templateCards() {
        displayedDeals = new ArrayList<Deal>();

        Deal example1 = new Deal("First Upload", "https://wallpapers.com/images/featured/among-us-cb21ldue3llceiua.jpg", "$10.00");
        displayedDeals.add(example1);
        Deal example2 = new Deal("Upload Rainbow", "https://images.ctfassets.net/hrltx12pl8hq/5GaLeZJlLyOiQC4gOA0qUM/a0398c237e9744ade8b072f99349e07a/shutterstock_152461202_thumb.jpg", "$16.00");
        displayedDeals.add(example2);
        Deal example3 = new Deal("Lake Upload", "https://img.freepik.com/free-photo/painting-mountain-lake-with-mountain-background_188544-9126.jpg", "$30.00");
        displayedDeals.add(example3);
        Deal example4 = new Deal("Autumn Road", "https://images.pexels.com/photos/1563356/pexels-photo-1563356.jpeg", "$20.00");
        displayedDeals.add(example4);
        Deal example5 = new Deal("Moon First", "https://img.freepik.com/free-photo/digital-art-moon-wallpaper_23-2150918877.jpg", "$23.00");
        displayedDeals.add(example5);

        updateList("");
    }




    private void setupSortSelect() {
        sortComboBox.setItems("Sorting");
        sortComboBox.setValue("");
        sortComboBox.addValueChangeListener(e -> setSort(e.getValue()));
    }

    private void setSort(String selectedSort) {

    }

    private void setupForumsDrop() {
        forumsDrop.setItems("","Profile","Plus");
        forumsDrop.setValue("");
        forumsDrop.addValueChangeListener(e -> setForumsDrop(e.getValue()));
    }

    private void setForumsDrop(String selectedDrop) {
        if("Profile".equals(selectedDrop)){
            UI.getCurrent().navigate("profile");
        }

        else if("Plus".equals(selectedDrop)){
            UI.getCurrent().navigate("addGame");
        }
    }

    private void updateList(String filter) {
        List<Deal> filteredDeals = displayedDeals.stream()
                .filter(deal -> deal.getTitle().toLowerCase().contains(filter.toLowerCase()))
                .collect(Collectors.toList());


        rowsLayout.removeAll();

        HorizontalLayout cardRow = new HorizontalLayout();
        int count = 0;
        int cardsPerRow = 5;
        for(int i = 0; i < filteredDeals.size();i++) {
            Deal deal = filteredDeals.get(i);

            Image thumbImage = new Image(deal.getThumb(), "Thumbnail");
            thumbImage.setMaxWidth(190, Unit.PIXELS);
            thumbImage.setMaxHeight(95, Unit.PIXELS);
            Anchor anchor = new Anchor("StorePageItem/" + deal.getTitle());
            anchor.getStyle().set("display", "block");
            anchor.getStyle().set("width", "100%");
            Card item = new Card(anchor,thumbImage, deal.getTitle(), deal.getNormalPrice(), null, deal);

            cardRow.add(item);
            count++;

            if (count % cardsPerRow == 0) {
                rowsLayout.add(cardRow);
                cardRow = new HorizontalLayout();
            }
        }

        rowsLayout.add(cardRow);
        rowsLayout.setAlignItems(FlexComponent.Alignment.CENTER);
    }

}