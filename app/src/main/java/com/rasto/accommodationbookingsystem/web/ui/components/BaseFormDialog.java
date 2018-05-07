package com.rasto.accommodationbookingsystem.web.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class BaseFormDialog extends Dialog {

    private final FormLayout formLayout = new FormLayout();
    private final HorizontalLayout buttonBar = new HorizontalLayout();
    private static final String CLASS_NAME_BUTTONS = "buttons";
    private static final String CLASS_NAME_PADDING = "has-padding";
    private static final String FORM_MIN_WIDTH = "250em";

    protected BaseFormDialog() {
        initFormLayout();
        initButtonBar();
        setCloseOnEsc(true);
        setCloseOnOutsideClick(false);
    }

    private void initButtonBar() {
        buttonBar.setClassName(CLASS_NAME_BUTTONS);
        buttonBar.setSpacing(true);
        add(buttonBar);
    }

    private void initFormLayout() {
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep(FORM_MIN_WIDTH, 1));
        Div div = new Div(formLayout);
        div.addClassName(CLASS_NAME_PADDING);
        add(div);
    }

    protected final FormLayout getFormLayout() {
        return formLayout;
    }

    protected void addButtons(Button ... buttons) {
        buttonBar.add(buttons);
    }
}
