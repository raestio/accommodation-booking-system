package com.rasto.accommodationreservationsystem.repository;

import com.rasto.accommodationreservationsystem.repository.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
