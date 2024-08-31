package com.example.demo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.rest.Deal;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;



@Route("StorePageItem")
@PageTitle("Page")
public class StorePageItem extends Composite<VerticalLayout> implements HasUrlParameter<String> {

    private int currentPage = 0;

    private Deal item;
    private Cart cart;
    private Image thumbImage;
    private H2 title;
    private Paragraph price;
    
    private final VerticalLayout column1 = new VerticalLayout();
    private final VerticalLayout column2 = new VerticalLayout();
    private final VerticalLayout column3 = new VerticalLayout();
    
    public StorePageItem() {
    	HorizontalLayout layoutRow = new HorizontalLayout();
        H1 h1 = new H1();
        h1.setText("G@meHub");
        layoutRow.add(h1);
        
        Button loginButton = new Button();
        Button signupButton = new Button();
        Button cart = new Button();
        Avatar avatar = new Avatar();

        loginButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("Login"))); // log in
        loginButton.setAutofocus(true);
        signupButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("Signup"))); // sign up
        signupButton.setAutofocus(true);

        HorizontalLayout tabs = new HorizontalLayout();
        
        loginButton.setText("Login");
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        signupButton.setText("Sign In");
        signupButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cart.setText("Cart");
        cart.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cart.addClickListener(e -> UI.getCurrent().navigate("shopping"));
        avatar.setName("Firstname Lastname");
        
        HorizontalLayout rowLayout2 = new HorizontalLayout();
        rowLayout2.add(layoutRow, tabs);
        tabs.add(loginButton, signupButton, cart, avatar);
        getContent().add(rowLayout2);
        
        
        thumbImage = new Image("https://archive.org/download/placeholder-image/placeholder-image.jpg", "Thumbnail");
        thumbImage.setWidth(600, Unit.PIXELS);
        thumbImage.setHeight(350, Unit.PIXELS);
    	
    	column1.add(thumbImage);
    	
        Section box2 = new Section();
        box2.setText("Author");
        box2.setMinWidth(550, Unit.PIXELS);
        box2.setMinHeight(100, Unit.PIXELS);
        
        title = new H2("Name Goes Here");
        price = new Paragraph("Price: $0.00");
        Paragraph descript = new Paragraph("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam nec blandit massa, at mattis odio. "
        		+ "Fusce id ante gravida urna rhoncus rutrum sed quis urna. In aliquam suscipit magna, egestas efficitur ante tincidunt vitae. "
        		+ "Mauris nunc quam, finibus et laoreet id, feugiat nec nisi. Morbi nisl augue, auctor id arcu id, vestibulum viverra felis. "
        		+ "Fusce rhoncus, mi sit amet posuere aliquam, diam ipsum ornare nibh, sit amet ornare nulla nunc at tellus. Aliquam pellentesque enim sed rutrum aliquet. "
        		+ "Aenean sapien leo, faucibus ac augue in, aliquam consequat odio. Donec faucibus auctor dapibus. Sed nec dui id felis consectetur venenatis ut sed erat. "
        		+ "Vestibulum nec velit sit amet ante condimentum mattis.");
        box2.add(title, price, descript);
        column2.add(box2);
        
        Section box3 = new Section();
        box3.setMinWidth(200, Unit.PIXELS);
        box3.setMinHeight(100, Unit.PIXELS);
        box3.getStyle().set( "border" , "1px solid Black" ); 
        
        Paragraph label = new Paragraph("Shopping Options");
        Button addToCart = new Button("Add To Cart");
        addToCart.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addToCart.addClickListener(e -> {
            VaadinSession vaadinSession = VaadinSession.getCurrent();
            ArrayList<Deal> shoppingCart = (ArrayList<Deal>) vaadinSession.getAttribute("shopGames");

            if (shoppingCart == null) {
                shoppingCart = new ArrayList<>();
            }

            if (!shoppingCart.contains(item)) {
                shoppingCart.add(item);
                vaadinSession.setAttribute("shopGames", shoppingCart); // Update the session
                // Navigate to the shopping cart page
                UI.getCurrent().navigate("shopping");
            } else {
                Notification.show("Item is already in the Shopping Cart");
            }
        });

        Button buyNow = new Button("Buy Now");
        buyNow.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        VerticalLayout buttons = new VerticalLayout();
        buttons.add(label, addToCart, buyNow);
        buttons.setAlignItems(FlexComponent.Alignment.CENTER);
        
        box3.add(buttons);
        column3.add(box3);
        
        
        HorizontalLayout rowLayout3 = new HorizontalLayout();
        rowLayout3.add(column1, column2, column3);
        getContent().add(rowLayout3);

    }

    //For some reason "String parameter" knows what the Query Parameter is. 
	@Override
	public void setParameter(BeforeEvent event, String parameter) {
		VaadinSession vaadinSession = VaadinSession.getCurrent();
		ArrayList<Deal> displayedDeals = (ArrayList<Deal>) vaadinSession.getAttribute("List");
		
		if(displayedDeals == null) {
			return;
		}
		
        for(Deal key: displayedDeals) {
			if(key.getTitle().equals(parameter)) {
				item = key;
			} 
		}	
        
        thumbImage.setSrc(item.getThumb());
        title.setText(item.getTitle());
    	price.setText("Price: " + item.getNormalPrice());
        
       
	}
}

