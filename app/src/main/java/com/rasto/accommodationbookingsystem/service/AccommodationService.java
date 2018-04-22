package com.rasto.accommodationbookingsystem.service;

import com.rasto.accommodationbookingsystem.repository.entity.Accommodation;

import java.util.List;

public interface AccommodationService {
    List<Accommodation> listAccommodations();
}
