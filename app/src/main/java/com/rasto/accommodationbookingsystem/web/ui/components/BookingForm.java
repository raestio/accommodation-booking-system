package com.rasto.accommodationbookingsystem.web.ui.components;

import com.rasto.accommodationbookingsystem.HasLogger;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasClickListeners;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Tag("booking-form")
@HtmlImport("src/components/booking-form.html")
public class BookingForm extends PolymerTemplate<BookingForm.Model> implements HasLogger {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Id("book")
    private Button bookButton;

    public interface Model extends TemplateModel {
        String getDateFrom();
        String getDateTo();
    }

    public BookingForm() {
        bookButton.addClickListener((ComponentEventListener<HasClickListeners.ClickEvent<Button>>) event -> {
            getLogger().info(getCheckInDate().toString());
            getLogger().info(getCheckOutDate().toString());
        });

        getElement().addPropertyChangeListener("dateTo", event -> {
            getLogger().info(event.getValue().toString());
        });
    }

    public LocalDate getCheckInDate() {
        return LocalDate.parse(getModel().getDateFrom(), formatter);
    }

    public LocalDate getCheckOutDate() {
        return LocalDate.parse(getModel().getDateTo(), formatter);
    }
}
