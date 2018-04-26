package com.rasto.accommodationbookingsystem.web.ui.views;

import com.rasto.accommodationbookingsystem.web.ui.MainLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

@Route(value = "accommodations", layout = MainLayout.class)
@Tag("accommodation-detail")
@HtmlImport("src/views/accommodation-detail-view.html")
public class AccommodationDetailView extends PolymerTemplate<TemplateModel> implements HasUrlParameter<Long> {

    private Long accommodationId;
    private final Label label = new Label();


    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        accommodationId = parameter;
    }
}
