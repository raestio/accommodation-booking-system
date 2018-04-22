package com.rasto.accommodationbookingsystem.repository;

import com.rasto.accommodationbookingsystem.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<User, Long> {
}
