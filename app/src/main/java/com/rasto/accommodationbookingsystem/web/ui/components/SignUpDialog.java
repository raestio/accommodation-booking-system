package com.rasto.accommodationbookingsystem.web.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
public class SignUpDialog extends BaseFormDialog {

    private final Button signUpButton = new Button("Sign Up");
    private final Button cancelButton = new Button("Cancel");
    private final TextField emailTextField = new TextField();
    private final TextField nameTextField = new TextField();
    private final TextField surnameTextField = new TextField();
    private final PasswordField passwordField = new PasswordField();

    public SignUpDialog() {
        super();
        initButtons();
        addButtons(signUpButton, cancelButton);
        initEmailField();
        initField(nameTextField, "Name");
        initField(surnameTextField, "Surname");
        initPasswordField();

    }

    private void initPasswordField() {
        passwordField.setLabel("Password");
        passwordField.setRequired(true);
        getFormLayout().add(passwordField);
    }

    private void initField(final TextField textField, final String label) {
        textField.setLabel(label);
        textField.setRequired(true);
        getFormLayout().add(textField);
    }


    private void initButtons() {
        signUpButton.setAutofocus(true);
        signUpButton.getElement().setAttribute("theme", "primary");
        cancelButton.addClickListener(e -> close());
    }

    private void initEmailField() {
        emailTextField.setLabel("Email");
        emailTextField.setRequired(true);
        getFormLayout().add(emailTextField);
        /*
        getBinder().forField(beverageName)
                .withConverter(String::trim, String::trim)
                .withValidator(new StringLengthValidator(
                        "Beverage name must contain at least 3 printable characters",
                        3, null))
                .bind(Review::getName, Review::setName);*/
    }

    /*private void initNameField() {
        beverageName.setLabel("Beverage");
        beverageName.setRequired(true);
        getFormLayout().add(beverageName);

        getBinder().forField(beverageName)
                .withConverter(String::trim, String::trim)
                .withValidator(new StringLengthValidator(
                        "Beverage name must contain at least 3 printable characters",
                        3, null))
                .bind(Review::getName, Review::setName);
    }*/
}
