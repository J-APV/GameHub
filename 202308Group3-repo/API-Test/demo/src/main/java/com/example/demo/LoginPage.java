package com.example.demo;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("Login")
@PageTitle("Log in • Game Hub")
public class LoginPage extends VerticalLayout implements BeforeEnterListener {
    public LoginForm login = new LoginForm();
    public LoginPage(){
        addClassName("login-page");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("Log in");

        add(new H1("Game Hub"), login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent){


    }

}
