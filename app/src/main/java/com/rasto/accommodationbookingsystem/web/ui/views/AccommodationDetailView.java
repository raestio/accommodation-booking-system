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

@Route(value = "accommodations", layout = MainLayout.class)
@Tag("accommodation-detail")
@HtmlImport("src/views/accommodation-detail-view.html")
public class AccommodationDetailView extends PolymerTemplate<AccommodationDetailView.Model> implements HasUrlParameter<Long>, HasLogger {

    public interface Model extends TemplateModel {
        String getDateFrom();
        String getDateTo();
    }

    private Long accommodationId;

    @Id("book")
    private Button bookButton;

    public AccommodationDetailView() {
        bookButton.addClickListener(new ComponentEventListener<HasClickListeners.ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(HasClickListeners.ClickEvent<Button> event) {
                getLogger().info(getCheckInDate());
                getLogger().info(getCheckOutDate());
            }
        });
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        accommodationId = parameter;
    }

    public String getCheckInDate() {
        return getModel().getDateFrom();
    }

    public String getCheckOutDate() {
        return getModel().getDateTo();
    }
}
