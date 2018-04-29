package com.rasto.accommodationbookingsystem.backend.service;

import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;
import com.rasto.accommodationbookingsystem.dto.AccommodationCardDTO;

import java.util.List;

public interface AccommodationService extends CrudService<Accommodation> {

    /**
     * @return List of accommodations cards that are displayed on home page
     */
    List<AccommodationCardDTO> findAllAccommodationsCards();
}
