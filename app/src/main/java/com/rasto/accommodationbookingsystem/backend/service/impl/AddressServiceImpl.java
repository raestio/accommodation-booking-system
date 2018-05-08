package com.rasto.accommodationbookingsystem.backend.service.impl;

import com.rasto.accommodationbookingsystem.backend.data.entity.Address;
import com.rasto.accommodationbookingsystem.backend.repository.AddressRepository;
import com.rasto.accommodationbookingsystem.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address createNew() {
        return new Address();
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    @Transactional
    public Address saveOrUpdate(Address entity) {
        return addressRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Address entity) {
        addressRepository.delete(entity);
    }

    @Override
    public Optional<Address> findByAddress(String country, String city, String street) {
        return addressRepository.findByCountryAndCityAndStreet(country, city, street);
    }
}
