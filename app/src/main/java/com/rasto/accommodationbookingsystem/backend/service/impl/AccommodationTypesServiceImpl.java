package com.rasto.accommodationbookingsystem.backend.service.impl;

import com.rasto.accommodationbookingsystem.backend.data.entity.AccommodationType;
import com.rasto.accommodationbookingsystem.backend.repository.AccommodationTypeRepository;
import com.rasto.accommodationbookingsystem.backend.service.AccommodationTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationTypesServiceImpl implements AccommodationTypesService {

    private final AccommodationTypeRepository accommodationTypeRepository;

    @Autowired
    public AccommodationTypesServiceImpl(AccommodationTypeRepository accommodationTypeRepository) {
        this.accommodationTypeRepository = accommodationTypeRepository;
    }

    @Override
    public AccommodationType createNew() {
        return new AccommodationType();
    }

    @Override
    public List<AccommodationType> findAll() {
        return accommodationTypeRepository.findAll();
    }

    @Override
    public Optional<AccommodationType> findById(Integer id) {
        return accommodationTypeRepository.findById(id);
    }

    @Override
    public AccommodationType saveOrUpdate(AccommodationType entity) {
        return accommodationTypeRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(Integer id) {
        accommodationTypeRepository.deleteById(id);
    }

    @Override
    public void delete(AccommodationType entity) {
        accommodationTypeRepository.delete(entity);
    }
}
