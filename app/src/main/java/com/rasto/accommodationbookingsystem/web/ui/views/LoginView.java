package com.rasto.accommodationbookingsystem.web.ui.views;

import com.rasto.accommodationbookingsystem.HasLogger;
import com.rasto.accommodationbookingsystem.web.ui.MainLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.rasto.accommodationbookingsystem.backend.constant.Constants.LOGIN_ERROR_MESSAGE;
import static com.rasto.accommodationbookingsystem.backend.constant.Constants.NOTIFICATION_DURATION_MS;

@Route(value = "login", layout = MainLayout.class)
@Tag("login-view")
@HtmlImport("src/views/login-view.html")
@PageTitle("Login")
public class LoginView extends PolymerTemplate<TemplateModel> implements HasLogger {

    @Id("emailField")
    private TextField emailField;

    @Id("passwordField")
    private PasswordField passwordField;

    @Id("loginButton")
    private Button loginButton;

    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    public LoginView(DaoAuthenticationProvider daoAuthenticationProvider) {
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        loginButton.addClickListener(event -> login());
        emailField.setAutofocus(true);
    }

    private void login() {
        try {
            Authentication auth = new UsernamePasswordAuthenticationToken(emailField.getValue(), passwordField.getValue());
            Authentication authenticated = daoAuthenticationProvider.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(authenticated);
            getUI().ifPresent(ui -> ui.navigate(""));
        } catch (AuthenticationException ex) {
            Notification.show(LOGIN_ERROR_MESSAGE, NOTIFICATION_DURATION_MS, Notification.Position.MIDDLE);
        }
    }

}
