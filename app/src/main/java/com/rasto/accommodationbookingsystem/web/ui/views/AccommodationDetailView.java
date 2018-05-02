package com.rasto.accommodationbookingsystem.web.ui.views;

import com.rasto.accommodationbookingsystem.HasLogger;
import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;
import com.rasto.accommodationbookingsystem.backend.data.entity.Booking;
import com.rasto.accommodationbookingsystem.backend.exception.AccommodationNotFoundException;
import com.rasto.accommodationbookingsystem.backend.exception.UserNotAuthenticatedException;
import com.rasto.accommodationbookingsystem.backend.service.AccommodationService;
import com.rasto.accommodationbookingsystem.backend.service.BookingsService;
import com.rasto.accommodationbookingsystem.security.UserAuthenticationState;
import com.rasto.accommodationbookingsystem.web.ui.MainLayout;
import com.rasto.accommodationbookingsystem.web.ui.components.BoardLayout;
import com.rasto.accommodationbookingsystem.web.ui.components.BookingForm;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Route(value = "accommodations", layout = MainLayout.class)
@Tag("accommodation-detail")
@HtmlImport("src/views/accommodation-detail-view.html")
public class AccommodationDetailView extends PolymerTemplate<TemplateModel> implements HasUrlParameter<Long>, HasDynamicTitle, HasLogger {

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

    private final AccommodationService accommodationService;
    private final BookingsService bookingsService;
    private final UserAuthenticationState userAuthenticationState;

    @Autowired
    public AccommodationDetailView(AccommodationService accommodationService, BookingsService bookingsService, UserAuthenticationState userAuthenticationState) {
        this.accommodationService = accommodationService;
        this.bookingsService = bookingsService;
        this.userAuthenticationState = userAuthenticationState;
        initBookingForm();
        photoModal.setVisible(false);
        photoModalClose.addClickListener(e -> photoModal.setVisible(false));
    }

    private void initBookingForm() {
        bookingForm.setOnCheckOutDateChangeListener((checkIn, checkOut) -> {
            if (bookingsService.existsBookedDayBetween(checkIn, checkOut, accommodationId)) {
                bookingForm.clearDatePicker();
            } else {
                try {
                    bookingForm.setTotalPrice(accommodationService.computeTotalBookingPrice(checkIn, checkOut, accommodationId));
                } catch (AccommodationNotFoundException e) {
                    e.printStackTrace();
                    bookingForm.disable();
                }
            }
        });

        bookingForm.setOnBookButtonClickListener((checkIn, checkOut) -> {
            if (userAuthenticationState.isAuthenticated()) {
                Booking booking = bookingsService.createNew();
                booking.setCheckIn(checkIn);
                booking.setCheckOut(checkOut);
                booking.setPrice(bookingForm.getTotalPrice());
                try {
                    bookingsService.bookAccommodation(booking, accommodationId, userAuthenticationState.getUserId());
                    showOnSuccessfullyBookedNotification();
                } catch (UserNotAuthenticatedException e) {
                    Notification.show(e.getMessage(), 5000, Notification.Position.MIDDLE);
                }

            } else {
                Notification.show("If you want to book this accommodation you have to log in or sign up.", 5000, Notification.Position.MIDDLE);
            }
        });
    }

    private void showOnSuccessfullyBookedNotification() {
        Label label = new Label("Accommodation successfully booked");
        Button button = new Button("OK");
        Notification notification = new Notification(label, button);
        button.addClickListener(e -> {
            notification.close();
            getUI().ifPresent(ui -> ui.getPage().reload());
        });
        notification.setPosition(Notification.Position.MIDDLE);
        notification.open();
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        accommodationId = parameter;
        Optional<Accommodation> accommodationOpt = accommodationService.findById(accommodationId);

        if (accommodationOpt.isPresent()) {
            Accommodation accommodation = accommodationOpt.get();
            setComponents(accommodation);
            disableBookedDays();
        } else {
            Notification.show("Accommodation does not exist", 3000, Notification.Position.MIDDLE);
            event.rerouteTo("");
        }
    }

    private void disableBookedDays() {
        List<LocalDate> bookedDays = bookingsService.getBookedDays(accommodationId);
        bookingForm.setDisabledDays(bookedDays);
    }

    private void setComponents(Accommodation accommodation) {
        accommodationName.setText(accommodation.getName());
        accommodationAddress.setText(accommodation.getAddress().getCountry() + ", " + accommodation.getAddress().getCity() + ", " + accommodation.getAddress().getStreet());
        guests.setText(accommodation.getGuests() + " " + (accommodation.getGuests() == 1 ? "guest" : "guests"));
        beds.setText(accommodation.getBathrooms() + " " + (accommodation.getBathrooms() == 1 ? "bed" : "beds"));
        baths.setText(accommodation.getBathrooms() + " " + (accommodation.getBathrooms() == 1 ? "bath" : "baths"));
        accommodation.getPhotos().forEach(photo -> photos.add(createAccommodationDetailImage(photo.getUrl())));

        bookingForm.setPrice(accommodation.getPricePerNight());
    }

    private Image createAccommodationDetailImage(String url) {
        Image image = new Image();
        image.setSrc(url);
        image.setClassName("photo");
        image.addListener(ClickEvent.class, event -> {
            photoModal.setVisible(true);
            photoModalImg.setSrc(((Image) event.getSource()).getSrc());
        });
        return image;
    }

    @Override
    public String getPageTitle() {
        return accommodationName != null ? accommodationName.getText() : "Accommodation";
    }
}
