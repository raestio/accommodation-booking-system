package com.rasto.accommodationbookingsystem.service;

import com.rasto.accommodationbookingsystem.repository.CustomerRepository;
import com.rasto.accommodationbookingsystem.repository.entities.User;
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
