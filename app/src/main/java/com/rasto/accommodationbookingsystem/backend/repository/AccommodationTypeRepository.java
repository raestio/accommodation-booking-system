package com.rasto.accommodationbookingsystem.backend.repository;

import com.rasto.accommodationbookingsystem.backend.data.entity.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, Integer> {
}
