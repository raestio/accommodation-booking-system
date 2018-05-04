package com.rasto.accommodationbookingsystem.backend.repository;

import com.rasto.accommodationbookingsystem.backend.data.AccommodationTypeEnum;
import com.rasto.accommodationbookingsystem.backend.data.entity.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, Integer> {
    Optional<AccommodationType> findByName(AccommodationTypeEnum name);
}
