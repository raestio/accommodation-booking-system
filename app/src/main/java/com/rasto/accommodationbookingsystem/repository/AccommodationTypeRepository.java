package com.rasto.accommodationbookingsystem.repository;

import com.rasto.accommodationbookingsystem.repository.entity.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, Integer> {
}
