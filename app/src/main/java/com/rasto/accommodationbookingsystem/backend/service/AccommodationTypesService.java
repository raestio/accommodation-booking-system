package com.rasto.accommodationbookingsystem.backend.service;

import com.rasto.accommodationbookingsystem.backend.data.AccommodationTypeEnum;
import com.rasto.accommodationbookingsystem.backend.data.entity.AccommodationType;

import java.util.Optional;

public interface AccommodationTypesService extends CrudService<AccommodationType, Integer> {
    Optional<AccommodationType> findByName(AccommodationTypeEnum name);
}
