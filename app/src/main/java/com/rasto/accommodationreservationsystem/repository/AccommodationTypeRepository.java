package com.rasto.accommodationreservationsystem.repository;

import com.rasto.accommodationreservationsystem.repository.entities.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, Integer> {
}
