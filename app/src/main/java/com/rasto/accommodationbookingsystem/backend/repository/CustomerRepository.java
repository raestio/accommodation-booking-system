package com.rasto.accommodationbookingsystem.backend.repository;

import com.rasto.accommodationbookingsystem.backend.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<User, Long> {
}
