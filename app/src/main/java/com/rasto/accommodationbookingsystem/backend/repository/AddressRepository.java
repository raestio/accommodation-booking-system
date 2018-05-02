package com.rasto.accommodationbookingsystem.backend.repository;

import com.rasto.accommodationbookingsystem.backend.data.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
