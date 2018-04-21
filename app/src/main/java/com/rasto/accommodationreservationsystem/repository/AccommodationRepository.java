package com.rasto.accommodationreservationsystem.repository;

import com.rasto.accommodationreservationsystem.repository.entities.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
