package com.example.application.views;

import com.example.application.data.User;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.discounts.GameDiscounts;

import com.example.application.views.store.ShoppingCartView;
import com.example.application.views.store.StorePageView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.NavigationEvent;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Overflow;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import com.vaadin.flow.theme.lumo.LumoUtility.Whitespace;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;
import java.io.ByteArrayInputStream;
import java.util.Optional;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    /**
     * A simple navigation item component, based on ListItem element.
     */
    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, Component icon, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            // Use Lumo classnames for various styling
            link.addClassNames(Display.FLEX, Gap.XSMALL, Height.MEDIUM, AlignItems.CENTER, Padding.Horizontal.SMALL,
                    TextColor.BODY);
            link.setRoute(view);

            Span text = new Span(menuTitle);
            // Use Lumo classnames for various styling
            text.addClassNames(FontWeight.MEDIUM, FontSize.MEDIUM, Whitespace.NOWRAP);

            if (icon != null) {
                link.add(icon);
            }
            link.add(text);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }

    }

    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;

        addToNavbar(createHeaderContent());
        setDrawerOpened(false);
    }

    private Component createHeaderContent() {
        Header header = new Header();
        header.addClassNames(BoxSizing.BORDER, Display.FLEX, FlexDirection.COLUMN, Width.FULL);

        Div layout = new Div();
        layout.addClassNames(Display.FLEX, AlignItems.CENTER, Padding.Horizontal.LARGE);

        H1 appName = new H1("G@meHub");
        appName.getStyle().set("font-size", "50px");
        appName.getStyle().set("color", "orange");
        appName.addClassNames(Margin.Vertical.MEDIUM, Margin.End.AUTO, FontSize.LARGE);
        layout.add(appName);
     
        layout.getStyle().set("background", "C:\\Users\\melor\\OneDrive\\Pictures\\Perlin (Contour).png");

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            Avatar avatar = new Avatar(user.getName());
            StreamResource resource = new StreamResource("profile-pic",
                    () -> new ByteArrayInputStream(user.getProfilePicture()));
            avatar.setImageResource(resource);
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add(user.getName());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            userName.getSubMenu().addItem("Sign out", e -> {
                authenticatedUser.logout();
            });
            userName.getSubMenu().addItem("Settings", e -> {
                openSettingsDialog();
            });

            layout.add(userMenu);
        } else {


            Anchor loginLink = new Anchor("login", "Log in");
            Anchor SignupLink = new Anchor("user-form", "Sign up");

            H3 mes = new H3("Sign up to view store!");

            HorizontalLayout hlayout = new HorizontalLayout();
            hlayout.add(mes ,SignupLink, loginLink);

            layout.add(hlayout);




        }

        Nav nav = new Nav();
        nav.addClassNames(Display.FLEX, Overflow.AUTO, Padding.Horizontal.MEDIUM, Padding.Vertical.XSMALL);

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames(Display.FLEX, Gap.SMALL, ListStyleType.NONE, Margin.NONE, Padding.NONE);
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            if (accessChecker.hasAccess(menuItem.getView())) {
                list.add(menuItem);
            }

        }

        header.add(layout, nav);
        return header;
    }
    private void openSettingsDialog() {
        // Create a confirm dialog
        VerticalLayout dialogLayout = new VerticalLayout();
        Dialog confirmDialog = new Dialog();
        confirmDialog.setModal(true);


        HorizontalLayout layoutRow = new HorizontalLayout();
        Icon icon = new Icon();
        Button buttonPrimary = new Button();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        TextField textField = new TextField();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        Anchor link = new Anchor();
        Anchor link2 = new Anchor();
        Hr hr = new Hr();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        RadioButtonGroup radioGroup = new RadioButtonGroup();
        HorizontalLayout layoutRow5 = new HorizontalLayout();
        Button buttonPrimary2 = new Button();
        layoutRow.setWidthFull();
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        icon.getElement().setAttribute("icon", "lumo:user");
        buttonPrimary.setText("Change");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidthFull();
        layoutRow2.setAlignItems(FlexComponent.Alignment.START);
        layoutRow2.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        textField.setLabel("Username");
        layoutRow3.setWidthFull();
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutRow3.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        link.setText("Change your password");
        link.setHref("#");
        layoutRow4.setWidthFull();
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutRow4.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        radioGroup.setLabel("UI Theme");
        radioGroup.setItems("Dark Mode", "Light Mode");

        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        layoutRow5.setWidthFull();
        layoutRow5.addClassName(Gap.MEDIUM);
        layoutRow5.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutRow5.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        buttonPrimary2.setText("Logout");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        dialogLayout.add(layoutRow);
        layoutRow.add(icon);
        layoutRow.add(buttonPrimary);
        dialogLayout.add(layoutRow2);
        layoutRow2.add(textField);
        dialogLayout.add(layoutRow3);
        layoutRow3.add(link);
        dialogLayout.add(hr);
        dialogLayout.add(layoutRow4);
        layoutRow4.add(radioGroup);
        dialogLayout.add(layoutRow5);
        layoutRow5.add(buttonPrimary2);


        // Add content to the dialog
        H1 dialogTitle = new H1("Settings");
        Span dialogContent = new Span("Are you sure you want to perform this action?");


        confirmDialog.add(dialogTitle, dialogLayout, dialogContent);


        // Add buttons for confirmation and cancellation
        Button confirmButton = new Button("Confirm");
        confirmButton.addClickListener(event -> {
            // Perform the action
            // Add your logic here

            // Close the dialog after the action is performed
            confirmDialog.close();
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.addClickListener(event -> confirmDialog.close());

        confirmDialog.add(confirmButton, cancelButton);

        // Open the confirm dialog
        confirmDialog.open();
    }


    private MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{ //
                new MenuItemInfo("Discounts", LineAwesomeIcon.PENCIL_RULER_SOLID.create(), GameDiscounts.class), //

                new MenuItemInfo("Store", LineAwesomeIcon.PENCIL_RULER_SOLID.create(), StorePageView.class), //

        };
    }

}
