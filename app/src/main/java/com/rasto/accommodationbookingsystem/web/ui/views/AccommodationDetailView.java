package com.rasto.accommodationbookingsystem.web.ui.views;

import com.rasto.accommodationbookingsystem.HasLogger;
import com.rasto.accommodationbookingsystem.web.ui.MainLayout;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasClickListeners;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Route(value = "accommodations", layout = MainLayout.class)
@Tag("accommodation-detail")
@HtmlImport("src/views/accommodation-detail-view.html")
public class AccommodationDetailView extends PolymerTemplate<AccommodationDetailView.Model> implements HasUrlParameter<Long>, HasLogger {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public interface Model extends TemplateModel {
        String getDateFrom();
        String getDateTo();
    }

    private Long accommodationId;

    @Id("book")
    private Button bookButton;

    public AccommodationDetailView() {
        bookButton.addClickListener((ComponentEventListener<HasClickListeners.ClickEvent<Button>>) event -> {
            getLogger().info(getCheckInDate().toString());
            getLogger().info(getCheckOutDate().toString());
        });
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        accommodationId = parameter;
    }

    public LocalDate getCheckInDate() {
        return LocalDate.parse(getModel().getDateFrom(), formatter);
    }

    public LocalDate getCheckOutDate() {
        return LocalDate.parse(getModel().getDateTo(), formatter);
    }
}
