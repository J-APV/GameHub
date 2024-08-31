package com.example.demo;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;


@Route("forums")
@PageTitle("Forums • Game Hub")
public class ForumsView extends Composite<VerticalLayout> {
    private final ComboBox<String> sortComboBox = new ComboBox<>("Sort");

    public ForumsView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H1 h1 = new H1();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        TabSheet tabSheet = new TabSheet();
        Button loginButton = new Button();
        Button signupButton = new Button();

        loginButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("Login"))); // log in
        loginButton.setAutofocus(true);
        signupButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("Signup"))); // sign up
        signupButton.setAutofocus(true);

        Avatar avatar = new Avatar();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        TextField searchBar = new TextField();
        HorizontalLayout layoutRow4 = new HorizontalLayout();

        HorizontalLayout layoutRow5 = new HorizontalLayout();
        Button buttonPrimary4 = new Button();
        Button buttonPrimary5 = new Button();

        HorizontalLayout layoutRow6 = new HorizontalLayout();
        Button buttonPrimary6 = new Button();
        Button buttonPrimary7 = new Button();
        HorizontalLayout layoutRow7 = new HorizontalLayout();
        getContent().setHeightFull();
        getContent().setWidthFull();
        layoutRow.setWidthFull();
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        h1.setText("G@meHub");
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
        layoutRow3.setAlignSelf(FlexComponent.Alignment.START, tabSheet);

        loginButton.setText("Login");
        layoutRow3.setAlignSelf(FlexComponent.Alignment.START, loginButton);
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        signupButton.setText("Sign In");
        layoutRow3.setAlignSelf(FlexComponent.Alignment.START, signupButton);
        signupButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        avatar.setName("Firstname Lastname");
        layoutRow3.setAlignSelf(FlexComponent.Alignment.START, avatar);
        layoutColumn3.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn3);
        layoutColumn4.setSpacing(false);
        layoutColumn4.addClassName(LumoUtility.Padding.XLARGE);
        layoutColumn4.setWidthFull();
        layoutColumn4.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutColumn4.setAlignItems(FlexComponent.Alignment.CENTER);
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
        layoutRow.add(h1);
        layoutRow.add(layoutRow2);
        layoutRow2.add(layoutColumn2);
        layoutColumn2.add(layoutRow3);
        layoutRow3.add(setTabSheetSampleData());
        layoutRow3.add(loginButton);
        layoutRow3.add(signupButton);
        layoutRow3.add(avatar);
        getContent().add(layoutColumn3);
        layoutColumn3.add(layoutColumn4);
        layoutColumn4.add(searchBar);
        layoutColumn3.add(layoutRow4);

        layoutRow4.add(sortComboBox);
        layoutRow4.add(layoutRow5);
        layoutRow5.add(buttonPrimary4);
        layoutRow5.add(buttonPrimary5);

        layoutColumn3.add(layoutRow6);
        layoutRow6.add(buttonPrimary6);
        layoutRow6.add(buttonPrimary7);
        getContent().add(layoutRow7);

    }


    private Tabs setTabSheetSampleData() {

        RouterLink homeLink = new RouterLink("Home", GameDiscounts.class);
        RouterLink storeLink = new RouterLink("Store Page", StorePageView.class);
        RouterLink forumsLink = new RouterLink("Forums", ForumsView.class);

        Tab current;
        Tabs tabs = new Tabs(
                new Tab(homeLink),
                new Tab(storeLink),
                current = new Tab(forumsLink)  // Add the RouterLink to the Tabs
        );
        tabs.setSelectedTab(current);

        return tabs;
    }




    private void setupSortSelect() {
        sortComboBox.setItems("Sorting");
        sortComboBox.setValue("");
        sortComboBox.addValueChangeListener(e -> setSort(e.getValue()));
    }

    private void setSort(String selectedSort) {

    }


}


