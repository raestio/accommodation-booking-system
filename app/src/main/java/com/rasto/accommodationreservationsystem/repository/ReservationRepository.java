package com.rasto.accommodationreservationsystem.repository;

import com.rasto.accommodationreservationsystem.repository.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
