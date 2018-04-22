package com.rasto.accommodationbookingsystem.repository;

import com.rasto.accommodationbookingsystem.repository.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
