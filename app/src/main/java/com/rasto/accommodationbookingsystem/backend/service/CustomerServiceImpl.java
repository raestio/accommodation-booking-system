package com.rasto.accommodationbookingsystem.backend.service;

import com.rasto.accommodationbookingsystem.backend.data.entity.User;
import com.rasto.accommodationbookingsystem.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(@Autowired CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public List<User> listCustomers() {
        return customerRepository.findAll();
    }
}
