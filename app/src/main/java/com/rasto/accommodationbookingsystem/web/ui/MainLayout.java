package com.rasto.accommodationbookingsystem.web.ui;

import com.rasto.accommodationbookingsystem.HasLogger;
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
public class MainLayout extends Div implements RouterLayout, PageConfigurator, BeforeEnterObserver, HasLogger {

    private final SignUpDialog signUpDialog;
    private final UserAuthenticationState userAuthenticationState;

    private Button signUp;
    private Button login;
    private Button bookings;
    private Button logout;
    private Button newAccommodation;

    @Autowired
    public MainLayout(SignUpDialog signUpDialog, UserAuthenticationState userAuthenticationState){
        this.signUpDialog = signUpDialog;
        this.userAuthenticationState = userAuthenticationState;
        H2 title = new H2("Airbnb");
        title.addClassName("main-layout-title");


        signUp = createSignUpButton();
        login = createLoginButton();
        logout = createLogoutButton();
        bookings = createBookingsButton();
        newAccommodation = createNewAccommodationButton();

        Div buttons = new Div(signUp, login, newAccommodation, bookings, logout);
        buttons.addClassName("main-layout-nav");
        Div header = new Div(title, buttons);
        header.addClassName("main-layout-header");
        add(header);
        addClassName("main-layout");
    }

    private Button createNewAccommodationButton() {
        newAccommodation = new Button();
        newAccommodation.setText("New accommodation");
        newAccommodation.setClassName("main-layout-item");
        newAccommodation.setVisible(false);
        newAccommodation.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate("new-accommodation"));
        });
        return newAccommodation;
    }

    private Button createSignUpButton() {
        signUp = new Button();
        signUp.setText("Sign Up");
        signUp.setClassName("main-layout-item");
        signUp.setVisible(false);
        signUp.addClickListener(e -> signUp());
        return signUp;
    }

    private Button createLoginButton() {
        login = new Button();
        login.setText("Log In");
        login.setClassName("main-layout-item");
        login.setVisible(false);
        login.addClickListener(e -> login());
        return login;
    }

    private Button createLogoutButton() {
        logout = new Button();
        logout.setText("Log Out");
        logout.setClassName("main-layout-item");
        logout.setVisible(false);
        logout.addClickListener(e -> logout());
        return logout;
    }

    private Button createBookingsButton() {
        bookings = new Button();
        bookings.setText("Bookings");
        bookings.setClassName("main-layout-item");
        bookings.setVisible(false);
        bookings.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate("bookings"));
        });
        return bookings;
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
            bookings.setVisible(true);
            if (userAuthenticationState.isLoggedUserAdmin()) {
                newAccommodation.setVisible(true);
            } else {
                newAccommodation.setVisible(false);
            }
        } else {
            bookings.setVisible(false);
            login.setVisible(true);
            signUp.setVisible(true);
            logout.setVisible(false);
            newAccommodation.setVisible(false);
        }
    }
}
