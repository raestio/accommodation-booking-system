package com.rasto.accommodationbookingsystem.service;

import com.rasto.accommodationbookingsystem.repository.entity.User;

import java.util.List;

public interface CustomerService {
    List<User> listCustomers();
}
