package com.rasto.accommodationbookingsystem.web.ui;

import com.rasto.accommodationbookingsystem.security.UserAuthenticationState;
import com.rasto.accommodationbookingsystem.web.ui.components.SignUpDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

@HtmlImport("frontend://styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Theme(Lumo.class)
public class MainLayout extends Div implements RouterLayout, PageConfigurator, BeforeEnterObserver {

    private final SignUpDialog signUpDialog;
    private final UserAuthenticationState userAuthenticationState;

    private Button signUp;
    private Button login;
    private Button logout;

    @Autowired
    public MainLayout(SignUpDialog signUpDialog, UserAuthenticationState userAuthenticationState){
        this.signUpDialog = signUpDialog;
        this.userAuthenticationState = userAuthenticationState;
        H2 title = new H2("Airbnb");
        title.addClassName("main-layout__title");


        signUp = createSignUpButton();
        login = createLoginButton();
        logout = createLogoutButton();

        Div buttons = new Div(signUp, login, logout);
        buttons.addClassName("main-layout__nav");
        Div header = new Div(title, buttons);
        header.addClassName("main-layout__header");
        add(header);
        addClassName("main-layout");
    }

    private Button createSignUpButton() {
        signUp = new Button();
        signUp.setText("Sign Up");
        signUp.setClassName("main-layout__nav-item");
        signUp.setVisible(false);
        signUp.addClickListener(e -> signUp());
        return signUp;
    }

    private Button createLoginButton() {
        login = new Button();
        login.setText("Login");
        login.setClassName("main-layout__nav-item");
        login.setVisible(false);
        login.addClickListener(e -> login());
        return login;
    }

    private Button createLogoutButton() {
        logout = new Button();
        logout.setText("Logout");
        logout.setClassName("main-layout__nav-item");
        logout.setVisible(false);
        logout.addClickListener(e -> logout());
        return logout;
    }

    private void logout() {
        getUI().ifPresent(ui -> {
            SecurityContextHolder.clearContext();
            ui.getSession().close();
            ui.navigate("");
            ui.getPage().reload();
        });
    }

    private void login() {
        getUI().ifPresent(ui -> ui.navigate("login"));
    }

    private void signUp() {
        signUpDialog.open();
    }

    @Override
    public void configurePage(InitialPageSettings initialPageSettings) {
        initialPageSettings.addMetaTag("apple-mobile-web-app-capable", "yes");
        initialPageSettings.addMetaTag("apple-mobile-web-app-status-bar-style", "black");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (userAuthenticationState.isAuthenticated()) {
            login.setVisible(false);
            signUp.setVisible(false);
            logout.setVisible(true);
        } else {
            login.setVisible(true);
            signUp.setVisible(true);
            logout.setVisible(false);
        }
    }
}
