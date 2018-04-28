package com.rasto.accommodationbookingsystem.web.ui.views;

import com.rasto.accommodationbookingsystem.HasLogger;
import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;
import com.rasto.accommodationbookingsystem.backend.service.AccommodationService;
import com.rasto.accommodationbookingsystem.web.ui.MainLayout;
import com.rasto.accommodationbookingsystem.web.ui.components.BookingForm;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
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

    @Id("bookingForm")
    private BookingForm bookingForm;

    private AccommodationService accommodationService;

    @Autowired
    public AccommodationDetailView(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
        bookingForm.setOnCheckOutDateChangeListener(new BookingForm.OnCheckOutDateChangeListener() {
            @Override
            public void onChange(LocalDate checkIn, LocalDate checkOut) {

            }
        });
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        accommodationId = parameter;
        Optional<Accommodation> accommodationOpt = accommodationService.findById(accommodationId);
        if (!accommodationOpt.isPresent()) {
            getUI().ifPresent(ui -> ui.navigate(""));
        }

        Accommodation accommodation = accommodationOpt.get();
        setComponents(accommodation);

    }

    private void setComponents(Accommodation accommodation) {
        accommodationName.setText(accommodation.getName());
        accommodationAddress.setText(accommodation.getAddress().getCountry() + ", " + accommodation.getAddress().getCity() + " " + accommodation.getAddress().getStreet());
        guests.setText(accommodation.getGuests() + " " + (accommodation.getGuests() == 1 ? "guest" : "guests"));
        beds.setText(accommodation.getBathrooms() + " " + (accommodation.getBathrooms() == 1 ? "bed" : "beds"));
        baths.setText(accommodation.getBathrooms() + " " + (accommodation.getBathrooms() == 1 ? "bath" : "baths"));
        bookingForm.setPrice(accommodation.getPricePerNight());
    }

}
