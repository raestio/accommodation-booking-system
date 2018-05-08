package com.rasto.accommodationbookingsystem.backend.service;

import com.rasto.accommodationbookingsystem.backend.data.entity.Booking;
import com.rasto.accommodationbookingsystem.backend.exception.UserNotAuthenticatedException;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.List;

public interface BookingsService extends CrudService<Booking, Long> {

    List<LocalDate> getBookedDays(Long accommodationId);

    /**
     * @param checkIn date from (inclusive)
     * @param checkOut date to (exclusive)
     * @param accommodationId accommodation id
     * @return true if some day exists between checkIn and checkOut range in accommodation (accommodationId) and is booked
     */
    boolean existsBookedDayBetween(LocalDate checkIn, LocalDate checkOut, Long accommodationId);

    /**
     * Make booking
     * @param booking booking
     * @param accommodationId accommodation id
     * @param userId user id
     */
    void bookAccommodation(Booking booking, Long accommodationId, Long userId) throws UserNotAuthenticatedException, DataIntegrityViolationException;

    List<Booking> getBookingsByUserIdOrderedByCheckIn(Long userId) throws UserNotAuthenticatedException;
}
