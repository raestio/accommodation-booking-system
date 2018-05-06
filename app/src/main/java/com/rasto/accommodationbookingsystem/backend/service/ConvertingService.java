package com.rasto.accommodationbookingsystem.backend.service;

import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;
import com.rasto.accommodationbookingsystem.backend.service.dto.AccommodationCardDTO;

public interface ConvertingService {
    <D> D convert(Object source, Class<D> destinationType);
    AccommodationCardDTO convertToAccommodationCardDTO(Accommodation accommodation);
}
