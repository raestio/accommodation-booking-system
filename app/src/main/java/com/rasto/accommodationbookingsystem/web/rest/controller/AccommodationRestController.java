package com.rasto.accommodationbookingsystem.web.rest.controller;

import com.rasto.accommodationbookingsystem.backend.service.AccommodationService;
import com.rasto.accommodationbookingsystem.backend.service.dto.AccommodationCardDTO;
import com.rasto.accommodationbookingsystem.backend.service.dto.AccommodationDTO;
import com.rasto.accommodationbookingsystem.web.rest.exception.AccommodationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/v1")
public class AccommodationRestController {

    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationRestController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping(value = "/accommodations")
    public Collection<AccommodationDTO> getAccommodations() {
        return accommodationService.findAllAndConvertToDTO();
    }

    @GetMapping(value = "/accommodations-cards")
    public Collection<AccommodationCardDTO> getAccommodationsCards() {
        return accommodationService.findAllAccommodationsCards();
    }

    @GetMapping(value = "/accommodations/{accommodationId}")
    public AccommodationDTO getAccommodation(@PathVariable Long accommodationId) {
        Optional<AccommodationDTO> optionalAccommodationDTO = accommodationService.findByIdAndConvertToDTO(accommodationId);
        if (optionalAccommodationDTO.isPresent()) {
            return optionalAccommodationDTO.get();
        }

        throw new AccommodationNotFoundException(accommodationId.toString());
    }
}
