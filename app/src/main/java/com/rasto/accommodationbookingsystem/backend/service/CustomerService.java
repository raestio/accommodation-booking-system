package com.rasto.accommodationbookingsystem.backend.service;

import com.rasto.accommodationbookingsystem.backend.data.entity.User;

import java.util.List;

public interface CustomerService {
    List<User> listCustomers();
}
