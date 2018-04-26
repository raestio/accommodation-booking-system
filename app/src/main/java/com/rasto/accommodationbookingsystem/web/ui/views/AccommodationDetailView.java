package com.rasto.accommodationbookingsystem.web.ui.views;

import com.rasto.accommodationbookingsystem.web.ui.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route(value = "accommodations", layout = MainLayout.class)
public class AccommodationDetailView extends Div implements HasUrlParameter<Long> {

    private Long accommodationId;
    private final Label label = new Label();

    public AccommodationDetailView() {
        add(label);
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        accommodationId = parameter;
        label.setText("Accommodation: " + accommodationId.toString());
    }
}
