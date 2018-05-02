package com.rasto.accommodationbookingsystem.web.ui.components;

import com.vaadin.flow.component.button.Button;

public class DropdownItem<T> extends Button {

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    private T value;
}
