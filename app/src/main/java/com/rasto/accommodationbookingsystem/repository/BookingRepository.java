package com.rasto.accommodationbookingsystem.repository;

import com.rasto.accommodationbookingsystem.repository.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
