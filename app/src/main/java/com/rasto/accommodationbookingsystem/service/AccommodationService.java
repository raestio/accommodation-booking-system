package com.rasto.accommodationbookingsystem.service;

import com.rasto.accommodationbookingsystem.repository.entities.Accommodation;

import java.util.List;

public interface AccommodationService {
    List<Accommodation> listAccommodations();
}
