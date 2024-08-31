package com.example.application.views.discounts;

import com.example.application.Card;
import com.example.application.data.UserRepository;
import com.example.application.views.MainLayout;


import com.example.application.views.UserForm;
import com.example.application.views.store.ShoppingCartView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
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
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@PageTitle("Discounts")
@Route(value = "my-view", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class GameDiscounts extends Composite<VerticalLayout> {

    private final UserRepository userRepository;

    private int currentPage = 0;

    private final ApiCall service;
    private List<Deal> displayedDeals;

    private final VerticalLayout rowsLayout = new VerticalLayout();


    private final ComboBox<String> storeComboBox = new ComboBox<>("Select a Store");
    private final ComboBox<String> sortComboBox = new ComboBox<>("Sort");

    public GameDiscounts(ApiCall service, UserRepository userRepository) {

        this.userRepository = userRepository;
        this.service = service;

        HorizontalLayout layoutRow = new HorizontalLayout();

        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        TextField searchBar = new TextField();
        HorizontalLayout layoutRow4 = new HorizontalLayout();

        Button buttonPrimary100 = new Button();
        buttonPrimary100.setText("Bookmarks");
        buttonPrimary100.addClickListener(e -> UI.getCurrent().navigate("bookmarks"));

        HorizontalLayout layoutRow5 = new HorizontalLayout();
        Button buttonPrimary4 = new Button("Previous Page", e -> previousPage());
        Button buttonPrimary5 = new Button("Next Page", e -> nextPage());

        HorizontalLayout layoutRow6 = new HorizontalLayout();
        Button buttonPrimary6 = new Button("Previous Page", e -> previousPage());
        Button buttonPrimary7 = new Button("Next Page", e -> nextPage());
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

        layoutColumn3.addClassName("layout-column3");
        layoutColumn3.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn3);
        layoutColumn4.setSpacing(false);
        layoutColumn4.addClassName(LumoUtility.Padding.XLARGE);
        layoutColumn4.setWidthFull();
        layoutColumn4.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutColumn4.setAlignItems(FlexComponent.Alignment.CENTER);
        searchBar.setLabel("Search by Title");
        searchBar.setWidthFull();
        searchBar.setPlaceholder("Enter game title...");
        searchBar.setValueChangeMode(ValueChangeMode.EAGER);
        searchBar.addValueChangeListener(e -> globalSearch(e.getValue()));
        layoutColumn4.setAlignSelf(FlexComponent.Alignment.CENTER, searchBar);
        layoutRow4.setWidthFull();
        layoutRow4.addClassName(LumoUtility.Gap.MEDIUM);

        layoutRow4.setAlignSelf(FlexComponent.Alignment.START, storeComboBox);


        layoutRow4.setAlignSelf(FlexComponent.Alignment.START, sortComboBox);
        setupStoreComboBox();
        setupSortSelect();

        layoutRow4.setFlexGrow(1.0, layoutRow5);
        layoutRow5.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow5.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutRow5.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        layoutRow5.setAlignSelf(FlexComponent.Alignment.END, buttonPrimary4);
        buttonPrimary4.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutRow5.setAlignSelf(FlexComponent.Alignment.END, buttonPrimary5);
        buttonPrimary5.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        layoutRow6.setWidthFull();
        layoutRow6.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow6.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutRow6.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        layoutRow6.setAlignSelf(FlexComponent.Alignment.END, buttonPrimary6);
        buttonPrimary6.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutRow6.setAlignSelf(FlexComponent.Alignment.END, buttonPrimary7);
        buttonPrimary7.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutRow7.setWidthFull();
        layoutRow7.addClassName(LumoUtility.Gap.MEDIUM);
        getContent().add(layoutRow);
        layoutRow.add(layoutRow2);
        layoutRow2.add(layoutColumn2);
        layoutColumn2.add(layoutRow3);


        getContent().add(layoutColumn3);
        layoutColumn3.add(layoutColumn4);
        layoutColumn4.add(searchBar);
        searchBar.addValueChangeListener(e -> updateList(e.getValue()));
        layoutColumn3.add(layoutRow4);
        layoutRow4.add(storeComboBox);
        layoutRow4.add(sortComboBox);
        layoutRow4.add(layoutRow5);
        layoutRow5.add(buttonPrimary4);
        layoutRow5.add(buttonPrimary5, buttonPrimary100);

        layoutColumn3.add(rowsLayout);
        layoutColumn3.addClassName("custom-scrollbar");

        setStoreID("Steam");
        updateList("");
        layoutColumn3.add(layoutRow6);
        layoutRow6.add(buttonPrimary6);
        layoutRow6.add(buttonPrimary7);
        getContent().add(layoutRow7);

    }


    private void setupStoreComboBox() {
        storeComboBox.setItems(getStoreNames());
        storeComboBox.setValue("Steam");
        storeComboBox.addValueChangeListener(e -> setStoreID(e.getValue()));

    }

    private List<String> getStoreNames() {
        return Arrays.asList(
                "Steam", "GamersGate", "GreenManGaming", "GOG", "Origin",
                "Humble Store", "Uplay", "Fanatical",
                "WinGameStore", "GameBillet", "Voidu", "Epic Games Store",
                "Gamesplanet", "Gamesload", "2Game", "IndieGala",
                "Blizzard Shop", "DLGamer", "Noctre", "DreamGame"
        );
    }

    private void setupSortSelect() {
        sortComboBox.setItems("", "Sort by Price Low -> High", "Sort by Price High -> Low", "Sort A-Z", "Sort Z-A");
        sortComboBox.setValue("");
        sortComboBox.addValueChangeListener(e -> setSort(e.getValue()));
    }




    private void nextPage() {
        currentPage++;
        setPageAndFetchDeals();
    }

    private void previousPage() {
        if (currentPage >= 1) {
            currentPage--;
            setPageAndFetchDeals();
        }
    }

    private void setPageAndFetchDeals() {
        service.setPageNumber(currentPage);
        displayedDeals = Arrays.asList(service.getDeals());
        updateList("");
    }

    private void globalSearch(String string) {
        service.setTitle(string);
        displayedDeals = Arrays.asList(service.getDeals());
        updateList(string);
        currentPage = 0;
        service.setPageNumber(currentPage);
    }

    private int setStoreID(String storeName) {
        int storeID;
        switch (storeName) {
            case "Steam":
                storeID = 1;
                break;
            case "GamersGate":
                storeID = 2;
                break;
            case "GreenManGaming":
                storeID = 3;
                break;
            case "GOG":
                storeID = 7;
                break;
            case "Origin":
                storeID = 8;
                break;
            case "Humble Store":
                storeID = 11;
                break;
            case "Uplay":
                storeID = 13;
                break;
            case "Fanatical":
                storeID = 15;
                break;
            case "WinGameStore":
                storeID = 21;
                break;
            case "GameBillet":
                storeID = 23;
                break;
            case "Voidu":
                storeID = 24;
                break;
            case "Epic Games Store":
                storeID = 25;
                break;
            case "Gamesplanet":
                storeID = 27;
                break;
            case "Gamesload":
                storeID = 28;
                break;
            case "2Game":
                storeID = 29;
                break;
            case "IndieGala":
                storeID = 30;
                break;
            case "Blizzard Shop":
                storeID = 31;
                break;
            case "DLGamer":
                storeID = 33;
                break;
            case "Noctre":
                storeID = 34;
                break;
            case "DreamGame":
                storeID = 35;
                break;
            default:
                storeID = 1; // Default to Store 1
        }

        // Set the storeID in the service
        service.setStoreID(storeID);
        storeComboBox.addValueChangeListener(event -> {

            sortComboBox.setValue("");
        });

        // Fetch deals for the selected store and update the displayed deals
        displayedDeals = Arrays.asList(service.getDeals());
        updateList(""); // Update the deal list with the new storeID
        return 1;
    }


    private void setSort(String selectedSort) {
        if ("Sort by Price Low -> High".equals(selectedSort)) {
            displayedDeals.sort(Comparator.comparing(Deal::getSalePrice));
        } else if ("Sort by Price High -> Low".equals(selectedSort)) {
            displayedDeals.sort(Comparator.comparing(Deal::getSalePrice).reversed());
        } else if ("Sort A-Z".equals(selectedSort)) {
            displayedDeals.sort(Comparator.comparing(Deal::getTitle));
        } else if ("Sort Z-A".equals(selectedSort)) {
            displayedDeals.sort(Comparator.comparing(Deal::getTitle).reversed());
        }

        updateList("");
    }

    private void updateList(String filter) {
        List<Deal> filteredDeals = displayedDeals.stream()
                .filter(deal -> deal.getTitle().toLowerCase().contains(filter.toLowerCase()))
                .toList();

        rowsLayout.removeAll();

        HorizontalLayout cardRow = new HorizontalLayout();
        int count = 0;
        int cardsPerRow = 5;
        for (int i = 0; i < filteredDeals.size(); i++) {
            Deal deal = filteredDeals.get(i);

            Image thumbImage = new Image(deal.getThumb(), "Thumbnail");
            String salePrice = "$" + deal.getSalePrice();
            String regularPrice = "$" + deal.getNormalPrice();
            Anchor anchor = new Anchor(deal.getLink());
            anchor.getStyle().set("display", "block");
            anchor.getStyle().set("width", "100%");

            Card item = new Card(anchor, thumbImage, deal.getTitle(), salePrice, regularPrice, deal, userRepository);
            // Add some space below each card by setting a margin
            // You can adjust the margin value as needed

            cardRow.add(item);
            cardRow.getStyle().set("margin", "40px 0  0");
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
