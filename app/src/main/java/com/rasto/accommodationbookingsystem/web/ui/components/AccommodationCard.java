package com.rasto.accommodationbookingsystem.web.ui.components;

import com.rasto.accommodationbookingsystem.backend.constant.Constants;
import com.vaadin.flow.component.HasClickListeners;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.math.BigDecimal;

@Tag("accommodation-card")
@HtmlImport("src/components/accommodation-card.html")
public class AccommodationCard extends PolymerTemplate<TemplateModel> implements HasClickListeners<AccommodationCard> {

    @Id("photo")
    private Image photo;
    @Id("name")
    private Label nameLabel;
    @Id("pricePerNight")
    private Label pricePerNightLabel;

    private Long accommodationId;
    private BigDecimal pricePerNight;

    public Long getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Long accommodationId) {
        this.accommodationId = accommodationId;
    }

    public void setPhoto(String url) {
        photo.setSrc(url);
    }

    public void setName(String name) {
        nameLabel.setText(name);
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
        pricePerNightLabel.setText(pricePerNight.toString() + " " + Constants.CURRENCY);
    }
}
