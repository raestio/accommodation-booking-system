package com.rasto.accommodationbookingsystem.web.ui.components;

import com.vaadin.flow.component.HasClickListeners;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("booking-item")
@HtmlImport("src/components/booking-item.html")
public class BookingItem extends PolymerTemplate<TemplateModel> implements HasClickListeners<BookingItem> {

    @Id("photo")
    private Image photo;

    @Id("city")
    private Span city;

    @Id("checkInCheckOut")
    private Span checkInCheckOut;

    @Id("price")
    private Span price;

    private Long accommodationId;

    public void setPhotoSrc(String src) {
        photo.setSrc(src);
    }

    public Span getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city.setText(city);
    }

    public void setCheckInCheckOut(String checkInCheckOut) {
        this.checkInCheckOut.setText(checkInCheckOut);
    }

    public void setPrice(String price) {
        this.price.setText(price);
    }

    public Long getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Long accommodationId) {
        this.accommodationId = accommodationId;
    }
}

