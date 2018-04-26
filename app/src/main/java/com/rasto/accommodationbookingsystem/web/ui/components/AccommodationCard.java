package com.rasto.accommodationbookingsystem.web.ui.components;

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
public class AccommodationCard extends PolymerTemplate<TemplateModel> {

    @Id("photo")
    private Image photo;
    @Id("name")
    private Label nameLabel;
    @Id("pricePerNight")
    private Label pricePerNightLabel;

    private Long accommodationId;
    private BigDecimal pricePerNight;

    public AccommodationCard() {
        /*setClassName("accommodation-card");
        add(photo);
        photo.setClassName("accommodation-card-image");
        Div cardTexts = new Div();
        VerticalLayout verticalLayout = new VerticalLayout(nameLabel, pricePerNightLabel);
        cardTexts.setClassName("accommodation-card-container");
        cardTexts.add(verticalLayout);
        add(cardTexts);*/
    }

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
        pricePerNightLabel.setText(pricePerNight.toString() + " CZK");
    }
}
