package com.rasto.accommodationbookingsystem.backend.repository;

import com.rasto.accommodationbookingsystem.backend.data.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByAccommodation_Id(Long accommodationId);

    @Query("SELECT b FROM Booking b LEFT OUTER JOIN Accommodation a ON b.accommodation.id = a.id WHERE a.id = :accommodationId AND ((:checkIn <= b.checkIn AND b.checkIn < :checkOut) OR ((:checkIn < b.checkOut AND b.checkOut < :checkOut)))")
    List<Booking> findAllWhereExistsBookedDayBetween(@Param("checkIn") LocalDate checkIn, @Param("checkOut") LocalDate checkOut, @Param("accommodationId") Long accommodationId);

    List<Booking> findAllByUser_IdOrderByCheckInAsc(Long userId);
}
