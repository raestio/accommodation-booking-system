package com.rasto.accommodationbookingsystem.web.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class BaseFormDialog extends Dialog {
    private final FormLayout formLayout = new FormLayout();
    private final HorizontalLayout buttonBar = new HorizontalLayout();

    protected BaseFormDialog() {
        initFormLayout();
        initButtonBar();
        setCloseOnEsc(true);
        setCloseOnOutsideClick(false);
    }

    private void initButtonBar() {
        buttonBar.setClassName("buttons");
        buttonBar.setSpacing(true);
        add(buttonBar);
    }

    private void initFormLayout() {
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("250em", 1));
        Div div = new Div(formLayout);
        div.addClassName("has-padding");
        add(div);
    }

    protected final FormLayout getFormLayout() {
        return formLayout;
    }

    protected void addButtons(Button ... buttons) {
        buttonBar.add(buttons);
    }
}
