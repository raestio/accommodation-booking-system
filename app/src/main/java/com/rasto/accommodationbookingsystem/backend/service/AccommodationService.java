package com.rasto.accommodationbookingsystem.backend.service;

import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;
import com.rasto.accommodationbookingsystem.backend.repository.AccommodationRepository;
import com.rasto.accommodationbookingsystem.dto.AccommodationCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccommodationService implements CrudService<Accommodation> {

    private final AccommodationRepository accommodationRepository;

    @Autowired
    public AccommodationService(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public JpaRepository<Accommodation, Long> getRepository() {
        return accommodationRepository;
    }

    /**
     * @return List of accommodations cards that are displayed on home page
     */
    public List<AccommodationCardDTO> findAllAccommodationsCards() {
        List<Accommodation> accommodations = accommodationRepository.findAll();
        return accommodations.stream().map(AccommodationCardDTO::create).collect(Collectors.toList());
    }

    @Override
    public Accommodation createNew() {
        return new Accommodation();
    }
}
