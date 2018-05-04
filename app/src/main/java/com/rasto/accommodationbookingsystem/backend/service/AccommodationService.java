package com.rasto.accommodationbookingsystem.backend.service;

import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;
import com.rasto.accommodationbookingsystem.backend.data.entity.AccommodationType;
import com.rasto.accommodationbookingsystem.backend.data.entity.Address;
import com.rasto.accommodationbookingsystem.backend.exception.AccommodationNotFoundException;
import com.rasto.accommodationbookingsystem.backend.service.dto.AccommodationCardDTO;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface AccommodationService extends CrudService<Accommodation, Long> {

    /**
     * @return List of accommodations that are displayed on home page
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

    /**
     * Save accommodation and it's photos
     * @param accommodation Accommodation
     * @param type Accommodation type
     * @param address Accommodation address
     * @param photos photos
     * @return the saved accommodation
     * @throws IOException if uploading images failed
     */
    Accommodation saveAccommodationWithPhotos(Accommodation accommodation, AccommodationType type, Address address, List<File> photos) throws IOException;
}
