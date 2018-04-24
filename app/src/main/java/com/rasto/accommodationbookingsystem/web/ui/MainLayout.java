package com.rasto.accommodationbookingsystem.web.ui;

import com.rasto.accommodationbookingsystem.web.ui.components.SignUpDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

@HtmlImport("frontend://styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Route("")
@Theme(Lumo.class)
public class MainLayout extends Div implements RouterLayout, PageConfigurator {

    private final SignUpDialog signUpDialog;

    @Autowired
    public MainLayout(SignUpDialog signUpDialog){
        this.signUpDialog = signUpDialog;
        H2 title = new H2("Airbnb");
        title.addClassName("main-layout__title");


        Div buttons = new Div(createSignUpButton(), createLoginButton());
        buttons.addClassName("main-layout__nav");

        Div header = new Div(title, buttons);
        header.addClassName("main-layout__header");
        add(header);
        addClassName("main-layout");
    }

    private Button createSignUpButton() {
        Button signUp = new Button();
        signUp.setText("Sign Up");
        signUp.setClassName("main-layout__nav-item");
        signUp.addClickListener(e -> signUp());
        return signUp;
    }

    private Button createLoginButton() {
        Button login = new Button();
        login.setText("Login");
        login.setClassName("main-layout__nav-item");
        login.addClickListener(e -> login());
        return login;
    }

    private void login() {

    }

    private void signUp() {
        signUpDialog.open();
    }

    @Override
    public void configurePage(InitialPageSettings initialPageSettings) {
        initialPageSettings.addMetaTag("apple-mobile-web-app-capable", "yes");
        initialPageSettings.addMetaTag("apple-mobile-web-app-status-bar-style", "black");
    }
}
