package com.rasto.accommodationbookingsystem.backend.service;

import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;
import com.rasto.accommodationbookingsystem.dto.AccommodationCardDTO;
import com.rasto.accommodationbookingsystem.exception.AccommodationNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface AccommodationService extends CrudService<Accommodation> {

    /**
     * @return List of accommodations cards that are displayed on home page
     */
    List<AccommodationCardDTO> findAllAccommodationsCards();

    /**
     * Compute the potential price of booking
     * @param checkIn check in
     * @param checkOut check out
     * @param accommodationId accommodation id
     * @return the total potential price
     */
    BigDecimal computeTotalBookingPrice(LocalDate checkIn, LocalDate checkOut, Long accommodationId) throws AccommodationNotFoundException;
}
