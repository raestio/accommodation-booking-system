package com.rasto.accommodationbookingsystem.backend.repository;

import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Query("SELECT a FROM Accommodation a WHERE (lower(a.address.country) LIKE lower(:countryOrCity)) OR (lower(a.address.city) LIKE lower(:countryOrCity))")
    List<Accommodation> findByCountryOrCityLikeIgnoreCase(@Param("countryOrCity") String countryOrCity);
}
