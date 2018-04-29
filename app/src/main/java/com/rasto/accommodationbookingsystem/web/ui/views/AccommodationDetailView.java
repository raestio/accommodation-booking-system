package com.rasto.accommodationbookingsystem.web.ui.views;

import com.rasto.accommodationbookingsystem.HasLogger;
import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;
import com.rasto.accommodationbookingsystem.backend.service.AccommodationService;
import com.rasto.accommodationbookingsystem.web.ui.MainLayout;
import com.rasto.accommodationbookingsystem.web.ui.components.BoardLayout;
import com.rasto.accommodationbookingsystem.web.ui.components.BookingForm;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Route(value = "accommodations", layout = MainLayout.class)
@Tag("accommodation-detail")
@HtmlImport("src/views/accommodation-detail-view.html")
public class AccommodationDetailView extends PolymerTemplate<TemplateModel> implements HasUrlParameter<Long>, HasLogger {

    private Long accommodationId;

    @Id("accommodationName")
    private Span accommodationName;

    @Id("accommodationAddress")
    private Span accommodationAddress;

    @Id("guests")
    private Span guests;

    @Id("beds")
    private Span beds;

    @Id("baths")
    private Span baths;

    @Id("photos")
    private BoardLayout photos;

    @Id("bookingForm")
    private BookingForm bookingForm;




    @Id("photoModal")
    private Div photoModal;

    @Id("photoModalClose")
    private Span photoModalClose;

    @Id("photoModalImg")
    private Image photoModalImg;

    private AccommodationService accommodationService;

    @Autowired
    public AccommodationDetailView(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
        bookingForm.setOnCheckOutDateChangeListener((checkIn, checkOut) -> {

        });
        photoModal.setVisible(false);
        photoModalClose.addClickListener(e -> photoModal.setVisible(false));
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        accommodationId = parameter;
        Optional<Accommodation> accommodationOpt = accommodationService.findById(accommodationId);

        if (accommodationOpt.isPresent()) {
            Accommodation accommodation = accommodationOpt.get();
            setComponents(accommodation);
        } else {
            Notification.show("Accommodation does not exist", 3000, Notification.Position.MIDDLE);
            event.rerouteTo("");
        }
    }

    private void setComponents(Accommodation accommodation) {
        accommodationName.setText(accommodation.getName());
        accommodationAddress.setText(accommodation.getAddress().getCountry() + ", " + accommodation.getAddress().getCity() + ", " + accommodation.getAddress().getStreet());
        guests.setText(accommodation.getGuests() + " " + (accommodation.getGuests() == 1 ? "guest" : "guests"));
        beds.setText(accommodation.getBathrooms() + " " + (accommodation.getBathrooms() == 1 ? "bed" : "beds"));
        baths.setText(accommodation.getBathrooms() + " " + (accommodation.getBathrooms() == 1 ? "bath" : "baths"));

        accommodation.getPhotos().forEach(photo -> photos.add(createImage(photo.getUrl())));

        bookingForm.setPrice(accommodation.getPricePerNight());
    }

    private Image createImage(String url) {
        Image image = new Image();
        image.setSrc(url);
        image.setClassName("photo");
        image.addListener(ClickEvent.class, event -> {
            photoModal.setVisible(true);
            photoModalImg.setSrc(((Image) event.getSource()).getSrc());
        });
        return image;
    }

}
