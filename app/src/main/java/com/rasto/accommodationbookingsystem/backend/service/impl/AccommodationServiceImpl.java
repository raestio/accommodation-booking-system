package com.rasto.accommodationbookingsystem.backend.service.impl;

import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;
import com.rasto.accommodationbookingsystem.backend.repository.AccommodationRepository;
import com.rasto.accommodationbookingsystem.backend.service.AccommodationService;
import com.rasto.accommodationbookingsystem.dto.AccommodationCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;

    @Autowired
    public AccommodationServiceImpl(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public List<AccommodationCardDTO> findAllAccommodationsCards()  {
        List<Accommodation> accommodations = accommodationRepository.findAll();
        return accommodations.stream().map(AccommodationCardDTO::create).collect(Collectors.toList());
    }

    @Override
    public Accommodation createNew() {
        return new Accommodation();
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Accommodation saveOrUpdate(Accommodation entity) {
        return accommodationRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Accommodation entity) {
        accommodationRepository.delete(entity);
    }
}
