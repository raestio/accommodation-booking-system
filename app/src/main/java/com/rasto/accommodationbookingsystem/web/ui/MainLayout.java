package com.rasto.accommodationbookingsystem.web.ui;

import com.rasto.accommodationbookingsystem.HasLogger;
import com.rasto.accommodationbookingsystem.backend.constant.MappingURLConstants;
import com.rasto.accommodationbookingsystem.security.UserAuthenticationState;
import com.rasto.accommodationbookingsystem.web.ui.components.SignUpDialog;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasClickListeners;
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

import static com.rasto.accommodationbookingsystem.backend.constant.Constants.*;

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

    private final String CLASS_NAME_BUTTON = "main-layout-item";
    private final String CLASS_NAME_TITLE = "main-layout-title";
    private final String CLASS_NAME_NAV = "main-layout-nav";
    private final String CLASS_NAME_HEADER = "main-layout-header";
    private final String CLASS_NAME_LAYOUT = "main-layout";

    @Autowired
    public MainLayout(SignUpDialog signUpDialog, UserAuthenticationState userAuthenticationState){
        this.signUpDialog = signUpDialog;
        this.userAuthenticationState = userAuthenticationState;
        H2 title = new H2(APP_TITLE);
        title.addClassName(CLASS_NAME_TITLE);
        title.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("")));

        signUp = createButton(SIGN_UP, CLASS_NAME_BUTTON, e -> signUp());
        login = createButton(LOG_IN, CLASS_NAME_BUTTON, e -> login());
        logout = createButton(LOG_OUT, CLASS_NAME_BUTTON, e -> logout());
        bookings = createButton(BOOKINGS, CLASS_NAME_BUTTON, e -> getUI().ifPresent(ui -> ui.navigate(MappingURLConstants.BOOKINGS)));
        newAccommodation = createButton(NEW_ACCOMMODATION, CLASS_NAME_BUTTON, e -> getUI().ifPresent(ui -> ui.navigate(MappingURLConstants.NEW_ACCOMMODATION)));

        Div buttons = new Div(signUp, login, newAccommodation, bookings, logout);
        buttons.addClassName(CLASS_NAME_NAV);
        Div header = new Div(title, buttons);
        header.addClassName(CLASS_NAME_HEADER);
        add(header);
        addClassName(CLASS_NAME_LAYOUT);
    }

    private Button createButton(String text, String className, ComponentEventListener<HasClickListeners.ClickEvent<Button>> buttonClickEvent) {
        Button button = new Button();
        button.setText(text);
        button.setClassName(className);
        button.setVisible(false);
        button.addClickListener(buttonClickEvent);
        return button;
    }

    private void logout() {
        getUI().ifPresent(ui -> {
            SecurityContextHolder.clearContext();
            ui.getSession().close();
            ui.navigate(MappingURLConstants.HOME);
            ui.getPage().reload();
        });
    }

    private void login() {
        getUI().ifPresent(ui -> ui.navigate(MappingURLConstants.LOG_IN));
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
