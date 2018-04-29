package com.rasto.accommodationbookingsystem.web.ui.views;

import com.rasto.accommodationbookingsystem.HasLogger;
import com.rasto.accommodationbookingsystem.web.ui.MainLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

@Route(value = "login", layout = MainLayout.class)
@Tag("login-view")
@HtmlImport("src/views/login-view.html")
public class LoginView extends PolymerTemplate<TemplateModel> implements HasLogger {

    @Id("emailField")
    private TextField emailField;

    @Id("passwordField")
    private PasswordField passwordField;

    @Id("loginButton")
    private Button loginButton;

    public LoginView() {
        loginButton.addClickListener(event -> login());
    }

    private void login() {
        getLogger().info("Login");
    }

}
