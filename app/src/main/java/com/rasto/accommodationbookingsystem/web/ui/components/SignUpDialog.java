package com.rasto.accommodationbookingsystem.web.ui.components;

import com.rasto.accommodationbookingsystem.HasLogger;
import com.rasto.accommodationbookingsystem.backend.data.entity.User;
import com.rasto.accommodationbookingsystem.backend.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SignUpDialog extends BaseFormDialog implements HasLogger {

    private final Button signUpButton = new Button("Sign Up");
    private final Button cancelButton = new Button("Cancel");
    private final TextField emailTextField = new TextField();
    private final TextField nameTextField = new TextField();
    private final TextField surnameTextField = new TextField();
    private final PasswordField passwordField = new PasswordField();

    private final Binder<User> binder = new Binder<>(User.class);
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpDialog(UserService userService, PasswordEncoder passwordEncoder) {
        super();
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        initUIComponents();
        bindUser();
    }

    private void bindUser() {
        binder.setBean(userService.createNew());
        binder.forField(emailTextField)
                .withValidator(new EmailValidator("Enter a valid email address"))
                .withValidator(email -> !userService.exists(email), "User already exists")
                .bind(user -> emailTextField.getEmptyValue(), User::setEmail);

        binder.forField(nameTextField)
                .withConverter(String::trim, String::trim)
                .withValidator(new StringLengthValidator(
                        "Name must contain at least 1 printable character",
                        1, null))
                .bind(user -> nameTextField.getEmptyValue(), User::setName);

        binder.forField(surnameTextField)
                .withConverter(String::trim, String::trim)
                .withValidator(new StringLengthValidator(
                        "Surname must contain at least 1 printable character",
                        1, null))
                .bind(user -> surnameTextField.getEmptyValue(), User::setSurname);

        binder.forField(passwordField).withValidator(pass -> pass.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$"), "Need 6 or more chars, mixing digits, letters and special characters")
                .bind(user -> passwordField.getEmptyValue(), (user, pass) -> {
                    if (!passwordField.getEmptyValue().equals(pass)) {
                        user.setPassword(passwordEncoder.encode(pass));
                    }
                });

        binder.addStatusChangeListener(e -> {
            boolean isValid = e.getBinder().isValid();
            signUpButton.setEnabled(isValid);
        });
    }

    private void initUIComponents() {
        initButtons();
        addButtons(signUpButton, cancelButton);
        initEmailField();
        initField(nameTextField, "Name");
        initField(surnameTextField, "Surname");
        initPasswordField();
    }

    private void initPasswordField() {
        passwordField.setLabel("Password");
        passwordField.setValueChangeMode(ValueChangeMode.EAGER);
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
        signUpButton.addClickListener(e -> signUp());
        signUpButton.setEnabled(false);
        cancelButton.addClickListener(e -> onClose());
    }

    private void onClose() {
        binder.removeBean();
        close();
    }

    private void signUp() {
        if (binder.validate().isOk()) {
            User user = binder.getBean();
            userService.saveOrUpdate(user);
            onClose();
        } else {
            signUpButton.setEnabled(false);
        }
    }

    private void initEmailField() {
        emailTextField.setLabel("Email (login)");
        emailTextField.setRequired(true);
        getFormLayout().add(emailTextField);
    }
}
