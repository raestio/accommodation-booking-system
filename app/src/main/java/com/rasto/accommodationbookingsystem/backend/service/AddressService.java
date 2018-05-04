package com.rasto.accommodationbookingsystem.backend.service;

import com.rasto.accommodationbookingsystem.backend.data.entity.Address;

import java.util.Optional;

public interface AddressService extends CrudService<Address, Long> {
    Optional<Address> findByAddress(String country, String city, String street);
}
