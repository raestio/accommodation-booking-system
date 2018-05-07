package com.rasto.accommodationbookingsystem.backend.service.impl;

import com.rasto.accommodationbookingsystem.HasLogger;
import com.rasto.accommodationbookingsystem.backend.data.entity.Booking;
import com.rasto.accommodationbookingsystem.backend.exception.UserNotAuthenticatedException;
import com.rasto.accommodationbookingsystem.backend.repository.BookingRepository;
import com.rasto.accommodationbookingsystem.backend.service.AccommodationService;
import com.rasto.accommodationbookingsystem.backend.service.BookingsService;
import com.rasto.accommodationbookingsystem.backend.service.UserService;
import com.rasto.accommodationbookingsystem.security.UserAuthenticationState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BookingServiceImpl implements BookingsService, HasLogger {

    private final static String USER_NOT_AUTHENTICATED_MESSAGE = "You are not authenticated. Something bad is going on here.";

    private final BookingRepository bookingRepository;
    private final UserAuthenticationState userAuthenticationState;
    private final AccommodationService accommodationService;
    private final UserService userService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, AccommodationService accommodationService, UserService userService, UserAuthenticationState userAuthenticationState) {
        this.bookingRepository = bookingRepository;
        this.accommodationService = accommodationService;
        this.userService = userService;
        this.userAuthenticationState = userAuthenticationState;
    }

    @Override
    public List<LocalDate> getBookedDays(Long accommodationId) {
        List<Booking> bookings = bookingRepository.findAllByAccommodation_Id(accommodationId);
        List<LocalDate> bookedDays = new ArrayList<>();

        bookings.stream().map(booking -> {
            long numOfDaysBetween = ChronoUnit.DAYS.between(booking.getCheckIn(), booking.getCheckOut());
            return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween)
                    .mapToObj(i -> booking.getCheckIn().plusDays(i))
                    .collect(Collectors.toList());
        }).forEachOrdered(bookedDays::addAll);
        return bookedDays;
    }

    @Override
    public boolean existsBookedDayBetween(LocalDate checkIn, LocalDate checkOut, Long accommodationId) {
        List<Booking> bookings = bookingRepository.findAllWhereExistsBookedDayBetween(checkIn, checkOut, accommodationId);
        return !bookings.isEmpty();
    }

    @Override
    public void bookAccommodation(Booking booking, Long accommodationId, Long userId) throws UserNotAuthenticatedException, DataIntegrityViolationException {
        if (userAuthenticationState.isAuthenticated() && userAuthenticationState.getUserId().equals(userId)) {
            booking.setAccommodation(accommodationService.findById(accommodationId).get());
            booking.setUser(userService.findById(userId).get());
            saveOrUpdate(booking);
        } else {
            throw new UserNotAuthenticatedException(USER_NOT_AUTHENTICATED_MESSAGE);
        }
    }

    @Override
    public List<Booking> getBookingsByUserIdOrderedByCheckIn(Long userId) throws UserNotAuthenticatedException {
        if (userAuthenticationState.isAuthenticated() && userAuthenticationState.getUserId().equals(userId)) {
            return bookingRepository.findAllByUser_IdOrderByCheckInAsc(userId);
        } else {
            throw new UserNotAuthenticatedException(USER_NOT_AUTHENTICATED_MESSAGE);
        }
    }

    @Override
    public Booking createNew() {
        return new Booking();
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    @Transactional
    public Booking saveOrUpdate(Booking entity) {
        return bookingRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Booking entity) {
        bookingRepository.delete(entity);
    }
}
