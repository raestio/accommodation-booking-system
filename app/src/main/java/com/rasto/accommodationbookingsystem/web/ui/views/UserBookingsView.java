package com.rasto.accommodationbookingsystem.web.ui.views;

import com.rasto.accommodationbookingsystem.backend.constant.Constants;
import com.rasto.accommodationbookingsystem.backend.constant.MappingURLConstants;
import com.rasto.accommodationbookingsystem.backend.data.entity.Booking;
import com.rasto.accommodationbookingsystem.backend.exception.UserNotAuthenticatedException;
import com.rasto.accommodationbookingsystem.backend.service.BookingsService;
import com.rasto.accommodationbookingsystem.security.UserAuthenticationState;
import com.rasto.accommodationbookingsystem.web.ui.MainLayout;
import com.rasto.accommodationbookingsystem.web.ui.components.BookingItem;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Route(value = "bookings", layout = MainLayout.class)
@Tag("bookings-view")
@HtmlImport("src/views/bookings-view.html")
@PageTitle("My Bookings")
public class UserBookingsView extends PolymerTemplate<TemplateModel> implements BeforeEnterObserver {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMATTER_PATTERN);

    @Id("bookingsVerticalLayout")
    private VerticalLayout bookingsVerticalLayout;

    private final BookingsService bookingService;
    private final UserAuthenticationState userAuthenticationState;

    @Autowired
    public UserBookingsView(BookingsService bookingService, UserAuthenticationState userAuthenticationState) {
        this.bookingService = bookingService;
        this.userAuthenticationState = userAuthenticationState;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!userAuthenticationState.isAuthenticated()) {
            event.rerouteTo(AccommodationsView.class);
            return;
        }

        try {
            bookingsVerticalLayout.removeAll();
            List<Booking> userBookings = bookingService.getBookingsByUserIdOrderedByCheckIn(userAuthenticationState.getUserId());
            setBookingsLayout(userBookings);
        } catch (UserNotAuthenticatedException e) {
            Notification.show(e.getMessage(), Constants.NOTIFICATION_DURATION_MS, Notification.Position.MIDDLE);
            getUI().ifPresent(ui -> ui.navigate(""));
        }
    }

    private void setBookingsLayout(List<Booking> userBookings) {
        userBookings.forEach(booking -> bookingsVerticalLayout.add(createBookingItem(booking)));
    }

    private BookingItem createBookingItem(Booking booking) {
        BookingItem bookingItem = new BookingItem();
        bookingItem.setPhotoSrc(booking.getAccommodation().getPhotos().get(0).getUrl());
        bookingItem.setAccommodationId(booking.getAccommodation().getId());
        bookingItem.setCity(booking.getAccommodation().getAddress().getCity());
        bookingItem.setPrice(booking.getPrice().toString() + " " + Constants.CURRENCY);
        bookingItem.setCheckInCheckOut(booking.getCheckIn().format(dateTimeFormatter) + " - " + booking.getCheckOut().format(dateTimeFormatter));
        bookingItem.addClickListener(item -> getUI().ifPresent(ui -> ui.navigate(MappingURLConstants.ACCOMMODATIONS + "/" + item.getSource().getAccommodationId())));
        return bookingItem;
    }
}
