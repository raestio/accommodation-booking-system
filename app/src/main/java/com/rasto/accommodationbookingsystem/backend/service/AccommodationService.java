package com.rasto.accommodationbookingsystem.backend.service;

import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;

import java.util.List;

public interface AccommodationService {
    List<Accommodation> listAccommodations();
}
