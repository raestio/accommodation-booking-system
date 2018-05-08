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
import com.rasto.accommodationbookingsystem.web.ui.utils.NotificationUtils;
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
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.rasto.accommodationbookingsystem.backend.constant.Constants.*;
import static com.rasto.accommodationbookingsystem.backend.constant.MappingURLConstants.HOME;

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

    private final static int LAST_N_DAYS_TO_DISABLE = 360;
    private final static String CLASS_NAME_PHOTO = "photo";

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

        bookingForm.setOnBookButtonClickListener(this::bookAccommodation);
    }

    private void bookAccommodation(LocalDate checkIn, LocalDate checkOut) {
        if (userAuthenticationState.isAuthenticated()) {
            Booking booking = bookingsService.createNew();
            booking.setCheckIn(checkIn);
            booking.setCheckOut(checkOut);
            booking.setPrice(bookingForm.getTotalPrice());
            try {
                bookingsService.bookAccommodation(booking, accommodationId, userAuthenticationState.getUserId());
                showOnSuccessfullyBookedNotification();
            } catch (DataIntegrityViolationException e) {
                Notification.show(ACCOMMODATION_ALREADY_BOOKED, NOTIFICATION_DURATION_MS, Notification.Position.MIDDLE);
            } catch (UserNotAuthenticatedException e) {
                Notification.show(e.getMessage(), NOTIFICATION_DURATION_MS, Notification.Position.MIDDLE);
            }

        } else {
            Notification.show(ACCOMMODATION_BOOKING_NOT_LOGGED_IN, NOTIFICATION_DURATION_MS, Notification.Position.MIDDLE);
        }
    }

    private void showOnSuccessfullyBookedNotification() {
        Notification notification = NotificationUtils.createNotification(ACCOMMODATION_BOOKED_SUCCESSFULLY, getUI());
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
            Notification.show(ACCOMMODATION_NOT_EXIST, NOTIFICATION_DURATION_MS, Notification.Position.MIDDLE);
            event.rerouteTo(HOME);
        }
    }

    private void disableBookedDays() {
        List<LocalDate> bookedDays = bookingsService.getBookedDays(accommodationId);
        bookedDays.addAll(getLastNDaysFromNow(LAST_N_DAYS_TO_DISABLE));
        bookingForm.setDisabledDays(bookedDays);
    }

    private List<LocalDate> getLastNDaysFromNow(int days) {
        LocalDate now = LocalDate.now();
        return IntStream.iterate(1, i -> i + 1).limit(days)
                .mapToObj(now::minusDays)
                .collect(Collectors.toList());
    }

    private void setComponents(Accommodation accommodation) {
        accommodationName.setText(accommodation.getName());
        accommodationAddress.setText(accommodation.getAddress().getCountry() + ", " + accommodation.getAddress().getCity() + ", " + accommodation.getAddress().getStreet());
        guests.setText(accommodation.getGuests() + " " + (accommodation.getGuests() == 1 ? GUEST : getPlural(GUEST)));
        beds.setText(accommodation.getBathrooms() + " " + (accommodation.getBathrooms() == 1 ? BED : getPlural(BED)));
        baths.setText(accommodation.getBathrooms() + " " + (accommodation.getBathrooms() == 1 ? BATH : getPlural(BATH)));
        accommodation.getPhotos().forEach(photo -> photos.add(createAccommodationDetailImage(photo.getUrl())));

        bookingForm.setPrice(accommodation.getPricePerNight());
    }

    private String getPlural(String str) {
        return str + "s";
    }

    private Image createAccommodationDetailImage(String url) {
        Image image = new Image();
        image.setSrc(url);
        image.setClassName(CLASS_NAME_PHOTO);
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
